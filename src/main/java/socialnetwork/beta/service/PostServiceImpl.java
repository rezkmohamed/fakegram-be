package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.PostDTO;
import socialnetwork.beta.entity.Post;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.repo.PostRepo;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.utils.PostUtils;
import socialnetwork.beta.utils.ProfileUtils;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ProfileRepo profileRepo;

	@Override
	@Transactional
	public List<PostDTO> findAllPosts() {
		List<Post> posts = postRepo.findAllPosts();
		List<PostDTO> postsDTO = PostUtils.postToCompleteDTO(posts);
		
		return postsDTO;
	}

	@Override
	@Transactional
	public List<PostDTO> findPostsProfilePage(String idProfile) {
		return PostUtils.postToDTO(postRepo.findPostsProfilePage(idProfile));
	}

	@Override
	@Transactional
	public PostDTO findPostById(String idPost) {		
		Post post = postRepo.findPostById(idPost);
		PostDTO postDTO = PostUtils.postToCompleteDTO(post);
		
		return postDTO;
	}

	@Override
	@Transactional
	public String savePost(PostDTO post) {
		Post postToSave = PostUtils.DTOPostToPostEntity(post);
		Profile profile = profileRepo.findProfile(post.getIdProfile());
		postToSave.setProfile(profile);
		
		return postRepo.savePost(postToSave);
	}

	@Override
	@Transactional
	public boolean deletePostById(String idProfile, String idPost) {
		Post post = postRepo.findPostById(idPost);
		if(!post.getProfile().getIdProfile().equals(idProfile)) {
			return false;
		}
		
		return postRepo.deletePostById(idPost);
	}

}
