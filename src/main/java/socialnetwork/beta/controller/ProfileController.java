package socialnetwork.beta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.service.ProfileService;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/{idProfile}")
	public ResponseEntity<ProfileDTO> findProfileById(@PathVariable String idProfile){
		ProfileDTO profile = profileService.findProfileById(idProfile);
		if(profile != null) {
			System.out.println("OKAY - " + profile);
			return new ResponseEntity<>(profile, HttpStatus.OK);
		}
		System.out.println("NOT OKAY");
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	

}
