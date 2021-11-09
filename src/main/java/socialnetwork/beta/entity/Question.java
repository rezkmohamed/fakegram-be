package socialnetwork.beta.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="questions")
public class Question {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_post")
	private String idQuestion;
	
	@Column(name="answered")
	private boolean isAnswered;
	
	@Column(name="isanonym")
	private boolean isAnonym;
	
	@Column(name="answer")
	private String answer;
	
	@Column(name="date")
	private Date date;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile_questioner")
	private Profile profileSender;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile_responder")
	private Profile profileReciver;
	
	public Question() {}
	
	public Question(String idQuestion, boolean isAnswered, boolean isAnonym, String answer, Date date,
			Profile profileSender, Profile profileReciver) {
		super();
		this.idQuestion = idQuestion;
		this.isAnswered = isAnswered;
		this.isAnonym = isAnonym;
		this.answer = answer;
		this.date = date;
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

	public Profile getProfileSender() {
		return profileSender;
	}

	public void setProfileSender(Profile profileSender) {
		this.profileSender = profileSender;
	}

	public Profile getProfileReciver() {
		return profileReciver;
	}

	public void setProfileReciver(Profile profileReciver) {
		this.profileReciver = profileReciver;
	}
}
