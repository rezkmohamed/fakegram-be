package socialnetwork.beta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.dto.PostDTO;
import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.service.PostService;
import socialnetwork.beta.service.ProfileService;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("")
	public ResponseEntity<List<PostDTO>> getAllPosts(){
		List<PostDTO> posts = postService.findAllPosts();
		
		if(posts == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/profile/{idProfile}")
	public ResponseEntity<List<PostDTO>> getPostsForProfilePage(@PathVariable String idProfile){
		ProfileDTO profile = profileService.findProfileById(idProfile);
		if(profile == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		List<PostDTO> posts = postService.findPostsProfilePage(idProfile);
		if(posts == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/detail/{idPost}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable String idPost){
		PostDTO post = postService.findPostById(idPost);
		if(post == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<HttpStatus> savePost(@RequestBody PostDTO postDTO){
		String response = postService.savePost(postDTO);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{idPost}")
	public ResponseEntity<HttpStatus> deletePostById(@PathVariable String idPost){
		if(postService.deletePostById(idPost)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
}
