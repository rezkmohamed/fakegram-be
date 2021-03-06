package socialnetwork.beta.service;

import java.util.List;

import socialnetwork.beta.dto.QuestionDTO;

public interface QuestionService {
	public List<QuestionDTO> findQuestionsForProfile(String idProfile);
	
	public List<QuestionDTO> findPendingQuestionsForProfile(String idProfile);
	
	public QuestionDTO findPendingQuestion(String idQuestion, String idProfileLogged);
	
	public String addNewQuestion(QuestionDTO questionDTO);
	
	public int updateQuestion(String idQuestion, String idProfileLogged,String answer);
	
	public int setQuestionToPending(String idQuestion, String idProfileLogged);
}
