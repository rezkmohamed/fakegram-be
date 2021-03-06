package socialnetwork.beta.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import socialnetwork.beta.dto.PostDTO;
import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.service.PostService;
import socialnetwork.beta.service.ProfileService;
import socialnetwork.beta.utils.RequestUtils;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("")
	public ResponseEntity<List<PostDTO>> getAllPosts(HttpServletRequest request){
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
	public ResponseEntity<HttpStatus> savePost(@RequestBody PostDTO postDTO, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		postDTO.setIdProfile(idProfile);
		String response = postService.savePost(postDTO);
		if(response == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/newpost")
	public ResponseEntity<HttpStatus> addPost(
			@RequestParam("myFile") MultipartFile file, 
			@RequestParam("description") String description,
			MultipartHttpServletRequest request) throws IllegalStateException, IOException {
		String idProfile = requestUtils.idProfileFromToken(request);
		String newIdPost = postService.savePostWithFileImg(file, description, idProfile);
		if(newIdPost == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{idPost}")
	public ResponseEntity<HttpStatus> deletePostById(@PathVariable String idPost, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		if(postService.deletePostById(idProfile, idPost)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
}
