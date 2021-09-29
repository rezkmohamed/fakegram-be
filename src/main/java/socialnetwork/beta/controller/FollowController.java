package socialnetwork.beta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.service.FollowService;
import socialnetwork.beta.service.ProfileService;

@RestController
@RequestMapping("followers")
public class FollowController {
	@Autowired
	private FollowService followService;
	@Autowired
	private ProfileService profileService;
	
	@PostMapping("/{idProfileFollower}/follows/{idProfileFollowed}")
	public ResponseEntity<HttpStatus> addNewFollow(@PathVariable String idProfileFollower, @PathVariable String idProfileFollowed){
		String newIdFollow = followService.addFollow(idProfileFollower, idProfileFollowed);
		if(newIdFollow == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{idProfileFollower}/unfollow/{idProfileFollowed}")
	public ResponseEntity<HttpStatus> deleteFollow(@PathVariable String idProfileFollower, @PathVariable String idProfileFollowed){
		if(followService.deleteFollow(idProfileFollower, idProfileFollowed)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
