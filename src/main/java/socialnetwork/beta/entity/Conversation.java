package socialnetwork.beta.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="conversation")
public class Conversation {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_conversation")
	private String idConversation;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile1")
	private Profile firstProfile;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_profile2")
	private Profile secondProfile;
	
	@OneToMany(fetch=FetchType.LAZY,
			   mappedBy="conversation",
			   cascade= CascadeType.ALL)
	private List<Message> messages;

	public Conversation() {}

	public String getIdConversation() {
		return idConversation;
	}

	public void setIdConversation(String idConversation) {
		this.idConversation = idConversation;
	}

	public Profile getFirstProfile() {
		return firstProfile;
	}

	public void setFirstProfile(Profile firstProfile) {
		this.firstProfile = firstProfile;
	}

	public Profile getSecondProfile() {
		return secondProfile;
	}

	public void setSecondProfile(Profile secondProfile) {
		this.secondProfile = secondProfile;
	}
	
	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}


}
