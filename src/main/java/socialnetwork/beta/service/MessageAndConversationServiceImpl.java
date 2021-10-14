package socialnetwork.beta.service;

import java.util.Comparator;
import java.util.List;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import socialnetwork.beta.dto.ConversationDTO;
import socialnetwork.beta.dto.MessageDTO;
import socialnetwork.beta.entity.Conversation;
import socialnetwork.beta.entity.Message;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public ConversationDTO createNewConversation(String idFirstProfile, String idSecondProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public boolean setMessagesAsSeen(String idConversation, String idLoggedProfile) {
		// TODO Auto-generated method stub
		return false;
	}

}
