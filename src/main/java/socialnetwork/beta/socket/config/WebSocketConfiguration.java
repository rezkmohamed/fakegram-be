package socialnetwork.beta.socket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;


/**
 * FIXME
 * @author Mohamed.Rezk
 *
 */
//@Configuration
@EnableWebSocket
public class WebSocketConfiguration extends WebSocketTransportRegistration implements WebSocketConfigurer{
//	@Value("${chatEndpoint}")
//	private String CHAT_ENDPOINT;
	
	
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
