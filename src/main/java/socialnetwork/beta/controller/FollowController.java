package socialnetwork.beta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.service.FollowService;
import socialnetwork.beta.service.ProfileService;
import socialnetwork.beta.utils.RequestUtils;

@RestController
@RequestMapping("followers")
public class FollowController {
	@Autowired
	private FollowService followService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("/checkfollow/{idProfileFollowed}")
	public ResponseEntity<Boolean> getIsFollowing(@PathVariable String idProfileFollowed, HttpServletRequest request){
		String idProfileFollower = requestUtils.idProfileFromToken(request);
		if(followService.checkFollow(idProfileFollower, idProfileFollowed)) {
			return new ResponseEntity<>(true,HttpStatus.OK);
		}
		
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
	
	@PostMapping("/follows/{idProfileFollowed}")
	public ResponseEntity<HttpStatus> addNewFollow(@PathVariable String idProfileFollowed, HttpServletRequest request){
		String idProfileFollower = requestUtils.idProfileFromToken(request);
		String newIdFollow = followService.addFollow(idProfileFollower, idProfileFollowed);
		if(newIdFollow == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/unfollow/{idProfileFollowed}")
	public ResponseEntity<HttpStatus> deleteFollow(@PathVariable String idProfileFollowed, HttpServletRequest request){
		String idProfileFollower = requestUtils.idProfileFromToken(request);
		if(followService.deleteFollow(idProfileFollower, idProfileFollowed)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
