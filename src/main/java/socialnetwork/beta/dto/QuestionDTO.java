package socialnetwork.beta.dto;

import java.util.Date;

public class QuestionDTO {
	private String idQuestion;
	private boolean isAnswered;
	private boolean isAnonym;
	private String question;
	private String answer;
	private Date date;
	private String idProfileSender;
	private String idProfileReciver;
	private ProfileDTO profileSender;
	private ProfileDTO profileReciver;
	
	public QuestionDTO() {}	

	public QuestionDTO(String idQuestion, boolean isAnswered, boolean isAnonym, String question, String answer,
			Date date, String idProfileSender, String idProfileReciver, ProfileDTO profileSender,
			ProfileDTO profileReciver) {
		super();
		this.idQuestion = idQuestion;
		this.isAnswered = isAnswered;
		this.isAnonym = isAnonym;
		this.question = question;
		this.answer = answer;
		this.date = date;
		this.idProfileSender = idProfileSender;
		this.idProfileReciver = idProfileReciver;
		this.profileSender = profileSender;
		this.profileReciver = profileReciver;
	}

	public String getIdQuestion() {
		return idQuestion;
	}

	public void setIdQuestion(String idQuestion) {
		this.idQuestion = idQuestion;
	}

	public boolean getIsAnswered() {
		return isAnswered;
	}

	public void setIsAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public boolean getIsAnonym() {
		return isAnonym;
	}

	public void setIsAnonym(boolean isAnonym) {
		this.isAnonym = isAnonym;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIdProfileSender() {
		return idProfileSender;
	}

	public void setIdProfileSender(String idProfileSender) {
		this.idProfileSender = idProfileSender;
	}

	public String getIdProfileReciver() {
		return idProfileReciver;
	}

	public void setIdProfileReciver(String idProfileReciver) {
		this.idProfileReciver = idProfileReciver;
	}

	public ProfileDTO getProfileSender() {
		return profileSender;
	}

	public void setProfileSender(ProfileDTO profileSender) {
		this.profileSender = profileSender;
	}

	public ProfileDTO getProfileReciver() {
		return profileReciver;
	}

	public void setProfileReciver(ProfileDTO profileReciver) {
		this.profileReciver = profileReciver;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "QuestionDTO [idQuestion=" + idQuestion + ", isAnswered=" + isAnswered + ", isAnonym=" + isAnonym
				+ ", question=" + question + ", answer=" + answer + ", date=" + date + ", idProfileSender="
				+ idProfileSender + ", idProfileReciver=" + idProfileReciver + ", profileSender=" + profileSender
				+ ", profileReciver=" + profileReciver + "]";
	}
}
