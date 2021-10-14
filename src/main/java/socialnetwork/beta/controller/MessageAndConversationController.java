package socialnetwork.beta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.service.MessageAndConversationService;
import socialnetwork.beta.utils.RequestUtils;

@RestController
@RequestMapping("conversations")
public class MessageAndConversationController {
	@Autowired
	private MessageAndConversationService messageAndConversationService;
	@Autowired
	private RequestUtils requestUtils;
	
	
}
