package socialnetwork.beta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.dto.ConversationDTO;
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
	
	
}
