package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import socialnetwork.beta.dto.ConversationDTO;
import socialnetwork.beta.dto.MessageDTO;
import socialnetwork.beta.repo.MessageAndConversationRepo;
import socialnetwork.beta.repo.ProfileRepo;

@Service
public class MessageAndConversationServiceImpl implements MessageAndConversationService{
	@Autowired
	private MessageAndConversationRepo messageAndConversationRepo;
	@Autowired
	private ProfileRepo profileRepo;

	@Override
	public List<MessageDTO> getAllMessages() {
		return null;
	}

	@Override
	public List<MessageDTO> getMessagesOfChat(String idConversation, String idProfileLogged) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ConversationDTO> getConversationsForProfile(String idProfileLogged) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConversationDTO getConversation(String idProfile1, String idProfile2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageDTO addMessage(MessageDTO message) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConversationDTO createNewConversation(String idFirstProfile, String idSecondProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setMessagesAsSeen(String idConversation, String idLoggedProfile) {
		// TODO Auto-generated method stub
		return false;
	}

}
