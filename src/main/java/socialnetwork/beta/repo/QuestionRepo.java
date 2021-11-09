package socialnetwork.beta.repo;

import java.util.List;

import socialnetwork.beta.entity.Question;

public interface QuestionRepo {
	public List<Question> findQuestionsForProfile(String idProfile);
	
	public List<Question> findPendingQuestionsForProfile(String idProfile);
	
	public String addNewQuestion(Question question);
}
