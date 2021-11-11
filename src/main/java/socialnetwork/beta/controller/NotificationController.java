package socialnetwork.beta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.dto.NotificationDTO;
import socialnetwork.beta.service.NotificationService;
import socialnetwork.beta.utils.RequestUtils;

@RestController
@RequestMapping("notifications")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("")
	public ResponseEntity<List<NotificationDTO>> getNotifications(HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		List<NotificationDTO> notificationsDTO = notificationService.getNotificationsForProfile(idProfile);
		if(notificationsDTO != null) {
			return new ResponseEntity<>(notificationsDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/setseen")
	public ResponseEntity<HttpStatus> setNewNotificationsAsSeen(HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);
		notificationService.setNotificationsAsSeenForProfile(idProfile);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
