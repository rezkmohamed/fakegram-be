package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.CommentDTO;
import socialnetwork.beta.entity.Comment;
import socialnetwork.beta.entity.Post;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.repo.CommentRepo;
import socialnetwork.beta.repo.NotificationRepo;
import socialnetwork.beta.repo.PostRepo;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.utils.CommentUtils;
import socialnetwork.beta.utils.NotificationUtils;


@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ProfileRepo profileRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private NotificationRepo notificationRepo;
	
	@Override
	@Transactional
	public List<CommentDTO> findAllCommentsForPost(String idPost) {
		List<CommentDTO> commentsDTO = CommentUtils.commentToCommentDTO(commentRepo.findAllCommentsForPost(idPost));
		
		return commentsDTO;
	}

	@Override
	@Transactional
	public String addComment(String idProfile, CommentDTO commentDTO) {
		Comment commentToAdd = CommentUtils.commentDTOToCommentEntity(commentDTO);
		Post post = postRepo.findPostById(commentDTO.getIdPost());
		commentToAdd.setPost(post);
		Profile profile = profileRepo.findProfile(idProfile);
		commentToAdd.setProfile(profile);
		String idNewComment = commentRepo.addComment(commentToAdd);
		if(idNewComment != null && !idProfile.equals(post.getProfile().getIdProfile())) {
			notificationRepo.addNewNotification(NotificationUtils.newNotificationEntityFromComment(commentToAdd));
		}	
		
		
		return idNewComment;
	}

}
