package socialnetwork.beta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.dto.QuestionDTO;
import socialnetwork.beta.service.QuestionService;
import socialnetwork.beta.utils.RequestUtils;

@RestController
@RequestMapping("/questions")
public class QuestionController {
	@Autowired
	private QuestionService questionService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("/answered/{idProfile}")
	public ResponseEntity<List<QuestionDTO>> getQuestionsForProfile(@PathVariable String idProfile){
		
		return null;
	}
	
	@GetMapping("/pending")
	public ResponseEntity<List<QuestionDTO>> getPendingQuestionsForProfile(HttpServletRequest request){
		
		return null;
	}
	
	@PostMapping("/new")
	public ResponseEntity<Boolean> addNewQuestion(@RequestBody QuestionDTO questionDTO) {
		
		return null;
	}
	
}
