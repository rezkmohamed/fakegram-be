package socialnetwork.beta.dto;

import java.util.Date;
import java.util.List;


public class CommentDTO {
	private String idComment;
	private String comment;
	private Date date;
	private String idPost;
	private String idProfile;
	private String nicknameProfile;
	private List<CommentLikeDTO> commentLikes;
	private int commentLikesCounter;
	private boolean isLiked;
	
	public CommentDTO() {}

	public String getIdComment() {
		return idComment;
	}

	public void setIdComment(String idComment) {
		this.idComment = idComment;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIdPost() {
		return idPost;
	}

	public void setIdPost(String idPost) {
		this.idPost = idPost;
	}

	public String getIdProfile() {
		return idProfile;
	}

	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}

	public String getNicknameProfile() {
		return nicknameProfile;
	}

	public void setNicknameProfile(String nicknameProfile) {
		this.nicknameProfile = nicknameProfile;
	}

	public List<CommentLikeDTO> getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(List<CommentLikeDTO> commentLikes) {
		this.commentLikes = commentLikes;
	}

	public int getCommentLikesCounter() {
		return commentLikesCounter;
	}

	public void setCommentLikesCounter(int commentLikesCounter) {
		this.commentLikesCounter = commentLikesCounter;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	@Override
	public String toString() {
		return "CommentDTO [idComment=" + idComment + ", comment=" + comment + ", date=" + date + ", idPost=" + idPost
				+ ", idProfile=" + idProfile + ", nicknameProfile=" + nicknameProfile + ", commentLikes=" + commentLikes
				+ ", commentLikesCounter=" + commentLikesCounter + ", isLiked=" + isLiked + "]";
	}
	
	

}
