package socialnetwork.beta.service;

import java.util.List;

import socialnetwork.beta.dto.QuestionDTO;

public interface QuestionService {
	public List<QuestionDTO> findQuestionsForProfile(String idProfile);
	
	public List<QuestionDTO> findPendingQuestionsForProfile(String idProfile);
	
	public String addNewQuestion(QuestionDTO questionDTO);
}
