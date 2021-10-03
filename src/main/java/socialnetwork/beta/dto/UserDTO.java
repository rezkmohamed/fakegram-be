package socialnetwork.beta.dto;

public class UserDTO {
	private String idUser;
	private String nickname;
	private String email;
	private String pass;
	
	public UserDTO() {}

	public UserDTO(String email, String pass) {
		super();
		this.email = email;
		this.pass = pass;
	}

	public UserDTO(String idUser, String nickname, String email, String pass) {
		super();
		this.idUser = idUser;
		this.nickname = nickname;
		this.email = email;
		this.pass = pass;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "UserDTO [idUser=" + idUser + ", nickname=" + nickname + ", email=" + email + ", pass=" + pass + "]";
	}
}
