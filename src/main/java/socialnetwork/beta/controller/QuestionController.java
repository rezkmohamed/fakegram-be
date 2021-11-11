package socialnetwork.beta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		List<QuestionDTO> questionsDTO = questionService.findQuestionsForProfile(idProfile);
		if(questionsDTO != null) {
			return new ResponseEntity<>(questionsDTO, HttpStatus.OK);
		}		
		
		return new ResponseEntity<>(questionsDTO, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pending")
	public ResponseEntity<List<QuestionDTO>> getPendingQuestionsForProfile(HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		List<QuestionDTO> questionsDTO = questionService.findPendingQuestionsForProfile(idProfile);
		if(questionsDTO != null) {
			return new ResponseEntity<>(questionsDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(questionsDTO, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pending/{idQuestion}")
	public ResponseEntity<QuestionDTO> getPendingQuestion(@PathVariable String idQuestion, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		QuestionDTO questionDTO = questionService.findPendingQuestion(idQuestion, idProfile);
		if(questionDTO != null) {
			return new ResponseEntity<>(questionDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/new")
	public ResponseEntity<Boolean> addNewQuestion(@RequestBody QuestionDTO questionDTO, HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);
		questionDTO.setIdProfileSender(idProfile);
		String idNewQuestion = questionService.addNewQuestion(questionDTO);
		if(idNewQuestion != null) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{idQuestion}/update/{answer}")
	public ResponseEntity<Boolean> updateQuestion(@PathVariable String idQuestion, @PathVariable String answer, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		int resultQuery = questionService.updateQuestion(idQuestion, idProfile, answer);
		if(resultQuery <= 0) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
	
	@PutMapping("/{idQuestion}/settopending")
	public ResponseEntity<Boolean> setQuestionToPending(@PathVariable String idQuestion, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		int resultQuery = questionService.setQuestionToPending(idQuestion, idProfile);
		if(resultQuery <= 0) {
			return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
