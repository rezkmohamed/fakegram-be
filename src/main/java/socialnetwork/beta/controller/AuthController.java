package socialnetwork.beta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.service.ProfileService;


@RestController("")
public class AuthController {
	@Autowired
	private ProfileService profileService;
	
	
//	@PostMapping("/login")
//	public ResponseEntity<User> login(@RequestBody User user){
//		
//	}

}
