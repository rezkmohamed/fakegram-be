package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.QuestionDTO;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.entity.Question;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.repo.QuestionRepo;
import socialnetwork.beta.utils.QuestionUtils;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionRepo questionRepo;
	@Autowired
	private ProfileRepo profileRepo;

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
		Profile profileSender = profileRepo.findProfile(questionDTO.getIdProfileSender());
		Profile profileReciver = profileRepo.findProfile(questionDTO.getIdProfileReciver());
		Question question = QuestionUtils.questionEntityFromDTO(questionDTO, profileSender, profileReciver);
		
		return questionRepo.addNewQuestion(question);
	}

	@Override
	@Transactional
	public int updateQuestion(String idQuestion, String idProfileLogged,String answer) {
		Profile profileLogged = profileRepo.findProfile(idProfileLogged);
		if(!profileLogged.getIdProfile().equals(idProfileLogged)) {
			return -1;
		}
		
		return questionRepo.updateQuestion(idQuestion, answer);
	}
}
