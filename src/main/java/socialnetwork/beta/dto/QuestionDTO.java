package socialnetwork.beta.dto;

import java.util.Date;

public class QuestionDTO {
	private String idQuestion;
	private boolean isAnswered;
	private boolean isAnonym;
	private String answer;
	private Date date;
	private String idProfileSender;
	private String idProfileReciver;
	private ProfileDTO profileSender;
	private ProfileDTO profileReciver;
	
	public QuestionDTO() {}

	public QuestionDTO(String idQuestion, boolean isAnswered, boolean isAnonym, String answer, Date date,
			String idProfileSender, String idProfileReciver, ProfileDTO profileSender, ProfileDTO profileReciver) {
		super();
		this.idQuestion = idQuestion;
		this.isAnswered = isAnswered;
		this.isAnonym = isAnonym;
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

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public boolean isAnonym() {
		return isAnonym;
	}

	public void setAnonym(boolean isAnonym) {
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

	@Override
	public String toString() {
		return "QuestionDTO [idQuestion=" + idQuestion + ", isAnswered=" + isAnswered + ", isAnonym=" + isAnonym
				+ ", answer=" + answer + ", date=" + date + ", idProfileSender=" + idProfileSender
				+ ", idProfileReciver=" + idProfileReciver + ", profileSender=" + profileSender + ", profileReciver="
				+ profileReciver + "]";
	}
}
