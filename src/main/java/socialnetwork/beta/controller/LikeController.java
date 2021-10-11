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

import socialnetwork.beta.dto.LikeDTO;
import socialnetwork.beta.service.LikeService;
import socialnetwork.beta.utils.RequestUtils;

@RestController
@RequestMapping("likes")
public class LikeController {
	@Autowired
	private LikeService likeService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("/check/{idPost}")
	public ResponseEntity<Boolean> getIsLiked(@PathVariable String idPost, HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);
		if(likeService.getLikeByProfileAndPost(idProfile, idPost)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(false, HttpStatus.OK);
		}
	}
	
	@PostMapping("/add/{idPost}")
	public ResponseEntity<Boolean> addLike(@PathVariable String idPost, HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);
		LikeDTO likeDTO = new LikeDTO();
		likeDTO.setIdProfile(idProfile); likeDTO.setIdPost(idPost);
		if(likeService.addLike(likeDTO) != null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{idPost}")
	public ResponseEntity<Boolean> deleteLike(@PathVariable String idPost, HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);
		if(likeService.deleteLike(idProfile, idPost)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(false, HttpStatus.OK);
	}
}
