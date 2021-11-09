package socialnetwork.beta.utils;

import java.util.ArrayList;
import java.util.List;

import socialnetwork.beta.dto.QuestionDTO;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.entity.Question;

public class QuestionUtils {
	public static QuestionDTO questionDTOFromEntity(Question question) {
		QuestionDTO questionDTO = new QuestionDTO();
		questionDTO.setDate(question.getDate());
		questionDTO.setAnonym(question.isAnonym());
		questionDTO.setAnswered(question.isAnswered());
		questionDTO.setProfileReciver(ProfileUtils.profileToDTO(question.getProfileReciver()));
		questionDTO.setIdProfileReciver(question.getProfileReciver().getIdProfile());
		if(!questionDTO.isAnonym()) {
			questionDTO.setProfileSender(ProfileUtils.profileToDTO(question.getProfileSender()));
			questionDTO.setIdProfileSender(question.getProfileSender().getIdProfile());
		}
		if(questionDTO.isAnswered()) {
			questionDTO.setAnswer(question.getAnswer());
		}
		
		return questionDTO;
	}
	
	public static List<QuestionDTO> questionDTOFromEntity(List<Question> questions){
		List<QuestionDTO> questionsDTO = new ArrayList<>();
		for(Question q : questions) {
			questionsDTO.add(questionDTOFromEntity(q));
		}
		
		return questionsDTO;
	}
	
	public static Question questionEntityFromDTO(QuestionDTO questionDTO, Profile profileSender, Profile profileReciver) {
		Question question = new Question();
		question.setIdQuestion(questionDTO.getIdQuestion());
		question.setAnonym(questionDTO.isAnonym());
		question.setAnswered(questionDTO.isAnswered());
		if(question.isAnswered()) {
			question.setAnswer(questionDTO.getAnswer());
		}
		question.setProfileReciver(profileReciver);
		question.setProfileSender(profileSender);
		
		return question;
	}
}
