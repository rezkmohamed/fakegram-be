package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.QuestionDTO;
import socialnetwork.beta.entity.Question;
import socialnetwork.beta.repo.QuestionRepo;
import socialnetwork.beta.utils.QuestionUtils;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepo questionRepo;

	@Override
	@Transactional
	public List<QuestionDTO> findQuestionsForProfile(String idProfile) {
		List<Question> questions = questionRepo.findQuestionsForProfile(idProfile);
		List<QuestionDTO> questionsDTO = QuestionUtils.questionDTOFromEntity(questions);
		
		return questionsDTO;
	}

	@Override
	@Transactional
	public List<QuestionDTO> findPendingQuestionsForProfile(String idProfile) {
		List<Question> questions = questionRepo.findPendingQuestionsForProfile(idProfile);
		List<QuestionDTO> questionsDTO = QuestionUtils.questionDTOFromEntity(questions);
		
		return questionsDTO;
	}

	@Override
	@Transactional
	public String addNewQuestion(QuestionDTO questionDTO) {
		Question question = QuestionUtils.questionEntityFromDTO(questionDTO);
		
		return questionRepo.addNewQuestion(question);
	}

}
