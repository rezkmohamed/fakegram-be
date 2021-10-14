package socialnetwork.beta.service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import socialnetwork.beta.dto.ConversationDTO;
import socialnetwork.beta.dto.MessageDTO;
import socialnetwork.beta.entity.Conversation;
import socialnetwork.beta.entity.Message;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.repo.MessageAndConversationRepo;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.utils.MessageAndConversationUtils;

import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageAndConversationServiceImpl implements MessageAndConversationService{
	@Autowired
	private MessageAndConversationRepo messageAndConversationRepo;
	@Autowired
	private ProfileRepo profileRepo;

	@Override
	@Transactional
	public List<MessageDTO> getAllMessages() {
		List<Message> messages = messageAndConversationRepo.getAllMessages();
		
		return MessageAndConversationUtils.messageEntityToDTO(messages);
	}

	@Override
	@Transactional
	public List<MessageDTO> getMessagesOfChat(String idConversation, String idProfileLogged) {
		Conversation cnv = messageAndConversationRepo.getConversation(idConversation);
		if(cnv == null) {
			return null;
		}
		
		if(!cnv.getFirstProfile().getIdProfile().equals(idProfileLogged) &&
				!cnv.getSecondProfile().getIdProfile().equals(idProfileLogged)) {
			return null;
		}
		List<Message> messagesOfChat = messageAndConversationRepo.getMessagesOfChat(idConversation);
		
		return MessageAndConversationUtils.messageEntityToDTO(messagesOfChat);
	}

	@Override
	@Transactional
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged) {
		List<Conversation> conversations = messageAndConversationRepo.getConversationsForProfile(idProfileLogged);
		List<ConversationDTO> ris = MessageAndConversationUtils.conversationEntityToDTO(conversations);
		
		for(ConversationDTO conv : ris) {
			conv.setMessages( conv.getMessages().stream().sorted(Comparator.comparing(
					MessageDTO::getDate,
					Comparator.reverseOrder()
					)).collect(Collectors.toList()));
			if(conv.getMessages().size() > 0) {
				MessageDTO latestMsg = conv.getMessages().get(0);
				conv.setLatestMessage(latestMsg.getMessage());
				conv.setLatestMessageDate(latestMsg.getDateMillis());
			}
		}
		
		
		
		return ris.stream().sorted(Comparator.comparing(ConversationDTO::getLatestMessageDate, 
				Comparator.reverseOrder())).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public ConversationDTO getConversation(String idProfile1, String idProfile2) {
		Conversation conversation = messageAndConversationRepo.getConversation(idProfile1, idProfile2);
		if(conversation == null) {
			return null;
		}
		
		return MessageAndConversationUtils.conversationEntityToDTO(conversation);
	}

	@Override
	@Transactional
	public MessageDTO addMessage(MessageDTO message) {
		Profile profile1 = profileRepo.findProfile(message.getIdProfileSender());
		Profile profile2 = profileRepo.findProfile(message.getIdProfileReciver());
		if(profile2 == null || profile1 == null) {
			return null;
		}
		Date now = new Date(System.currentTimeMillis());
		
		Message msg = new Message(now, false);
		msg.setMessage(message.getMessage());
		msg.setProfileSender(profile1);
		msg.setProfileReciver(profile2);
		
		Conversation conversation = messageAndConversationRepo.getConversation(message.getIdConversation());
		msg.setConversation(conversation);
		String newIdMsg = messageAndConversationRepo.addMessage(msg);
		message.setIdMessage(newIdMsg);
		messageAndConversationRepo.addMessage(msg);
		
		return message;
	}

	@Override
	@Transactional
	public ConversationDTO createNewConversation(String idFirstProfile, String idSecondProfile) {
		Profile firstProfile = profileRepo.findProfile(idFirstProfile);
		if(firstProfile == null) {
			return null;
		}
		Profile secondProfile = profileRepo.findProfile(idSecondProfile);
		if(secondProfile == null) {
			return null;
		}
		Conversation conversation = new Conversation();
		
		conversation.setFirstProfile(firstProfile); 
		conversation.setSecondProfile(secondProfile);
		String idConversation = messageAndConversationRepo.createNewConversation(conversation);
		ConversationDTO ris = MessageAndConversationUtils.conversationEntityToDTO(conversation);
		ris.setIdConversation(idConversation);
		
		return ris;
	}

	@Override
	@Transactional
	public boolean setMessagesAsSeen(String idConversation, String idLoggedProfile) {
		Conversation conversation = messageAndConversationRepo.getConversation(idConversation);
		if(!conversation.getFirstProfile().getIdProfile().equals(idLoggedProfile) && 
				!conversation.getSecondProfile().getIdProfile().equals(idLoggedProfile)) {
			return false;
		}
		
		return messageAndConversationRepo.setMessagesAsSeen(idConversation, idLoggedProfile);
	}

}
