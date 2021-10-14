package socialnetwork.beta.socket.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import socialnetwork.beta.utils.RequestUtils;

public class ChatWebSocketHandler extends TextWebSocketHandler{
	@Autowired
	private RequestUtils requestUtils;
	
}
