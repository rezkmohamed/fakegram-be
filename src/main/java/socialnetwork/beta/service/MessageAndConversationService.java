package socialnetwork.beta.service;

import java.util.List;

import socialnetwork.beta.dto.ConversationDTO;
import socialnetwork.beta.dto.MessageDTO;

public interface MessageAndConversationService {
	public List<MessageDTO> getAllMessages();
	
	public List<MessageDTO> getMessagesOfChat(String idConversation, String idProfileLogged);
	
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged);
	
	public ConversationDTO getConversation(String idProfile1, String idProfile2);
	
	public MessageDTO addMessage(MessageDTO message);
	
	public ConversationDTO createNewConversation(String idFirstProfile, String idSecondProfile);
	
	public boolean setMessagesAsSeen(String idConversation, String idLoggedProfile);

}
