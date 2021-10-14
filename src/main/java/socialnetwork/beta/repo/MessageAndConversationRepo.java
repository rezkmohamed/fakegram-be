package socialnetwork.beta.repo;

import java.util.List;

import socialnetwork.beta.entity.Conversation;
import socialnetwork.beta.entity.Message;


public interface MessageAndConversationRepo {
	public List<Message> getAllMessages();
	
	public List<Message> getMessagesOfChat(String idConversation);
		
	public List<Conversation> getConversationsForProfile(String idProfileLogged);
	
	public Conversation getConversation(String idConversation);
	
	public Conversation getConversation(String idProfile1, String idProfile2);
	
	public boolean setMessagesAsSeen(String idConversation, String idLoggedProfile);
	
	public String addMessage(Message message);
	
	public String createNewConversation(Conversation conversation);

}
