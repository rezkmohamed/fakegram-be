package socialnetwork.beta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.dto.ConversationDTO;
import socialnetwork.beta.dto.MessageDTO;
import socialnetwork.beta.service.MessageAndConversationService;
import socialnetwork.beta.utils.RequestUtils;

@RestController
@RequestMapping("conversations")
public class MessageAndConversationController {
	@Autowired
	private MessageAndConversationService messageAndConversationService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("")
	public ResponseEntity<List<ConversationDTO>> getConversationsForProfile(HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		return new ResponseEntity<>(messageAndConversationService.getConversationsForProfile(idProfile), HttpStatus.OK);
	}
	
	@GetMapping("/{idConversation}/messages")
	public ResponseEntity<List<MessageDTO>> getMessagesForConversation(@PathVariable String idConversation, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		List<MessageDTO> messages = messageAndConversationService.getMessagesOfChat(idConversation, idProfile);
		return new ResponseEntity<>(messages, HttpStatus.OK);
		
	}
	
	@PostMapping("/new/{idSecondProfile}")
	public ResponseEntity<ConversationDTO> createNewConversation(@PathVariable String idSecondProfile, HttpServletRequest request){
		String idFirstProfile = requestUtils.idProfileFromToken(request);
		ConversationDTO conversation = messageAndConversationService.getConversation(idFirstProfile, idSecondProfile);
		if(conversation != null) {
			return new ResponseEntity<>(conversation, HttpStatus.OK);
		}
		ConversationDTO newConversation = messageAndConversationService.createNewConversation(idFirstProfile, idSecondProfile);
		
		if(newConversation == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(newConversation, HttpStatus.OK);
	}
	
	
	
}
