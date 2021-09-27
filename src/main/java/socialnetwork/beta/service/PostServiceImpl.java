package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.PostDTO;
import socialnetwork.beta.entity.Post;
import socialnetwork.beta.repo.PostRepo;
import socialnetwork.beta.utils.PostUtils;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;

	@Override
	@Transactional
	public List<PostDTO> findAllPosts() {
		return PostUtils.postToDTO(postRepo.findAllPosts());
	}

	@Override
	@Transactional
	public List<PostDTO> findPostsProfilePage(String idProfile) {
		return PostUtils.postToDTO(postRepo.findPostsProfilePage(idProfile));
	}

	@Override
	@Transactional
	public PostDTO findPostById(String idPost) {		
		return PostUtils.postToDTO(postRepo.findPostById(idPost));
	}

	@Override
	@Transactional
	public String savePost(PostDTO post) {
		Post postToSave = PostUtils.DTOPostToPostEntity(post);
		
		return postRepo.savePost(postToSave);
	}

	@Override
	@Transactional
	public boolean deletePostById(String idPost) {
		return postRepo.deletePostById(idPost);
	}

}
