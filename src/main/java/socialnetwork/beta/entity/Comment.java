package socialnetwork.beta.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="id_comment")
	private String idComment;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date")
	private Date date;
	
	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
                        CascadeType.REFRESH, CascadeType.DETACH})
	@JoinColumn(name="id_post")
	private Post post;

	@ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
            CascadeType.REFRESH, CascadeType.DETACH, CascadeType.REMOVE})
	@JoinColumn(name="id_profile")
	private Profile profile;
	
	@OneToMany(mappedBy="comment",
			   cascade=CascadeType.ALL,
			   fetch=FetchType.LAZY)
	private List<CommentLike> commentLikes;

	
	public Comment() {}

	public Comment(String comment, Date date) {
		super();
		this.comment = comment;
		this.date = date;
	}

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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	
	public List<CommentLike> getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(List<CommentLike> commentLikes) {
		this.commentLikes = commentLikes;
	}
	
	public void addCommentlike(CommentLike commentLike) {
		if(this.commentLikes == null) {
			this.commentLikes = new ArrayList<>();
		}
		
		this.commentLikes.add(commentLike);
	}

	@Override
	public String toString() {
		return "Comment [idComment=" + idComment + ", comment=" + comment + ", date=" + date + ", post=" + post
				+ ", profile=" + profile + ", commentLikes=" + commentLikes + "]";
	}
}
