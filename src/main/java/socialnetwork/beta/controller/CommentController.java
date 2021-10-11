package socialnetwork.beta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.dto.CommentDTO;
import socialnetwork.beta.service.CommentService;
import socialnetwork.beta.utils.RequestUtils;

@RestController
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("/{idPost}")
	public ResponseEntity<List<CommentDTO>> getCommentsForPost(@PathVariable String idPost){
		List<CommentDTO> comments = commentService.findAllCommentsForPost(idPost);
		
		if(comments == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
 	
	
	@PostMapping("/add")
	public ResponseEntity<HttpStatus> addCommentToPost(@RequestBody CommentDTO commentDTO, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		if(commentService.addComment(idProfile, commentDTO) == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
