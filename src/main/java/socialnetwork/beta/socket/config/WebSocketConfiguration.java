package socialnetwork.beta.socket.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import socialnetwork.beta.socket.handler.ChatWebSocketHandler;

/**
 * FIXME
 * @author Mohamed.Rezk
 *
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration extends WebSocketTransportRegistration implements WebSocketConfigurer{
	@Value("chatEndpoint")
	private String CHAT_ENDPOINT;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry
		.addHandler(getChatWebSocketHandler(), CHAT_ENDPOINT)
		.setAllowedOrigins("*");
	}
	
    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(20 * 10000);
        container.setMaxBinaryMessageBufferSize(3* 512 * 1024);
        return container;
    }
	
	@Bean
	public WebSocketHandler getChatWebSocketHandler() {
		return new ChatWebSocketHandler();
	}	
}
