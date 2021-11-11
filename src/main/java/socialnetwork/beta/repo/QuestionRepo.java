package socialnetwork.beta.repo;

import java.util.List;

import socialnetwork.beta.entity.Question;

public interface QuestionRepo {
	public List<Question> findQuestionsForProfile(String idProfile);
	
	public List<Question> findPendingQuestionsForProfile(String idProfile);
	
	public Question findPendingQuestion(String idQuestion);
	
	public Question findQuestionById(String idQuestion);
	
	public String addNewQuestion(Question question);
	
	public int updateQuestion(String idQuestion, String answer);
	
	public int setQuestionToPending(String idQuestion);
}
