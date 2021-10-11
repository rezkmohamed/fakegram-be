package socialnetwork.beta.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import socialnetwork.beta.dto.CommentDTO;
import socialnetwork.beta.entity.Comment;

public class CommentUtils {
	
	public static CommentDTO commentToCommentDTO(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		commentDTO.setComment(comment.getComment());
		commentDTO.setDate(comment.getDate());
		commentDTO.setIdPost(comment.getPost().getIdPost());
		commentDTO.setIdProfile(comment.getProfile().getIdProfile());
		commentDTO.setNicknameProfile(comment.getProfile().getNickname());
		
		return commentDTO;
	}
	
	public static List<CommentDTO> commentToCommentDTO(List<Comment> comments){
		List<CommentDTO> ris = new ArrayList<>(); 
		for(Comment c : comments) {
			ris.add(commentToCommentDTO(c));
		}
		
		return ris;
	}
	
	public static Comment commentDTOToCommentEntity(CommentDTO commentDTO) {
		Comment comment = new Comment(commentDTO.getComment(), new Date());
		
		return comment;
	}

}
