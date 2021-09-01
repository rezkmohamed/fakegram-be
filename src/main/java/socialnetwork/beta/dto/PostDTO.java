package socialnetwork.beta.dto;

import java.util.Date;
import java.util.List;

public class PostDTO {
	private String idPost;
	private String img;
	private String description;
	private Date date;
	private String idProfile;
	private ProfileDTO profile;
	private int commentsCounter;
	private int likesCounter;
	private List<CommentDTO> comments;
	private List<LikeDTO> likes;
	private boolean isLiked;
	
	
	public PostDTO() {}

	public PostDTO(String idPost, String urlImg, String description, String idProfile) {
		super();
		this.idPost = idPost;
		this.img = urlImg;
		this.description = description;
		this.idProfile = idProfile;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getUrlImg() {
		return img;
	}

	public void setUrlImg(String urlImg) {
		this.img = urlImg;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}
	
	public int getCommentsCounter() {
		return commentsCounter;
	}

	public void setCommentsCounter(int commentsCounter) {
		this.commentsCounter = commentsCounter;
	}

	public int getLikesCounter() {
		return likesCounter;
	}

	public void setLikesCounter(int likesCounter) {
		this.likesCounter = likesCounter;
	}

	public List<CommentDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}

	public List<LikeDTO> getLikes() {
		return likes;
	}

	public void setLikes(List<LikeDTO> likes) {
		this.likes = likes;
	}
	
	public ProfileDTO getProfile() {
		return profile;
	}

	public void setProfile(ProfileDTO profile) {
		this.profile = profile;
	}
	
	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "PostDTO [idPost=" + idPost + ", img=" + img + ", description=" + description + ", date=" + date
				+ ", idProfile=" + idProfile + ", profile=" + profile + ", commentsCounter=" + commentsCounter
				+ ", likesCounter=" + likesCounter + ", comments=" + comments + ", likes=" + likes + ", isLiked="
				+ isLiked + "]";
	}
}
