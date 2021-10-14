package socialnetwork.beta.utils;

import java.util.LinkedList;
import java.util.List;

import socialnetwork.beta.dto.ConversationDTO;
import socialnetwork.beta.dto.MessageDTO;
import socialnetwork.beta.entity.Conversation;
import socialnetwork.beta.entity.Message;

public class MessageAndConversationUtils {
	public static MessageDTO messageEntityToDTO(Message message) {
		MessageDTO ris = new MessageDTO(message.getIdMessage(),
				message.getProfileSender().getIdProfile(), 
				message.getProfileReciver().getIdProfile(),
				message.getMessage(),
				message.getDate().getTime(), message.isSeen());
		ris.setIdConversation(message.getConversation().getIdConversation());
		return ris;
	}
	
	public static List<MessageDTO> messageEntityToDTO(List<Message> messages){
		List<MessageDTO> ris = new LinkedList<>();
		for(Message m : messages) {
			ris.add(messageEntityToDTO(m));
		}
		
		return ris;
	}
	
	public static ConversationDTO conversationEntityToDTO(Conversation conversation) {
		ConversationDTO ris = new ConversationDTO();
		ris.setIdConversation(conversation.getIdConversation());
		ris.setFirstProfile(ProfileUtils.profileToDTO(conversation.getFirstProfile()));
		ris.setSecondProfile(ProfileUtils.profileToDTO(conversation.getSecondProfile()));	
		if(conversation.getMessages() != null) {
			ris.setMessages(messageEntityToDTO(conversation.getMessages()));
		}
		
		return ris;
	}
	
	public static List<ConversationDTO> conversationEntityToDTO(List<Conversation> conversations){
		List<ConversationDTO> ris = new LinkedList<>();
		for(Conversation c : conversations) {
			ris.add(conversationEntityToDTO(c));
		}
		
		return ris;
	}
}
