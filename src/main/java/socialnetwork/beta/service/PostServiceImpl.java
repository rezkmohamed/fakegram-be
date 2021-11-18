package socialnetwork.beta.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import socialnetwork.beta.dto.PostDTO;
import socialnetwork.beta.entity.Post;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.repo.PostRepo;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.utils.ImgUtils;
import socialnetwork.beta.utils.PostUtils;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ProfileRepo profileRepo;
	@Autowired
	private ImgUtils imgUtils;
	@Value("${basePathFileSystem}")
	private String basePathFileSystem;
	
	private void setProPicToPost(PostDTO postDTO) {
		if(postDTO.getProfile().getProPic() != null) {
			try {
				postDTO.getProfile().setProPic(imgUtils.fileImgToBase64Encoding(postDTO.getProfile().getProPic()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setProPicToPosts(List<PostDTO> postsDTO) {
		postsDTO.stream()
		.forEach(p -> {
			if(p.getProfile().getProPic() != null) {
				try {
					p.getProfile().setProPic(imgUtils.fileImgToBase64Encoding(p.getProfile().getProPic()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void setImgToPost(List<PostDTO> posts) {
		posts.stream()
		.forEach(p -> {
			try {
				p.setImg(imgUtils.fileImgToBase64Encoding(p.getImg()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	@Override
	@Transactional
	public List<PostDTO> findAllPosts() {
		List<Post> posts = postRepo.findAllPosts();
		List<PostDTO> postsDTO = PostUtils.postToCompleteDTO(posts);
		setImgToPost(postsDTO);
		setProPicToPosts(postsDTO);
		
		return postsDTO;
	}

	@Override
	@Transactional
	public List<PostDTO> findPostsProfilePage(String idProfile) {
		List<PostDTO> posts = PostUtils.postToDTO(postRepo.findPostsProfilePage(idProfile));
		setImgToPost(posts);
		
		return posts;
	}

	@Override
	@Transactional
	public PostDTO findPostById(String idPost) {		
		Post post = postRepo.findPostById(idPost);
		PostDTO postDTO = PostUtils.postToCompleteDTO(post);
		try {
			postDTO.setImg(imgUtils.fileImgToBase64Encoding(postDTO.getImg()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		setProPicToPost(postDTO);
		
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

	@Override
	@Transactional
	public String savePostWithFileImg(MultipartFile img, String description, String idProfile) {
		String filename = img.getOriginalFilename();
		String extension  = filename.substring(filename.lastIndexOf(".") + 1);
		Post postToSave = new Post();
		Profile profile = profileRepo.findProfile(idProfile);
		postToSave.setProfile(profile);
		postToSave.setDescription(description);
		
		if(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png")) {
			String newImg = UUID.randomUUID().toString()+ "." + extension;
			try {
				img.transferTo(new File(basePathFileSystem + newImg));
				postToSave.setImg_post(newImg);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return postRepo.savePost(postToSave);
		}

		return null;
	}
}
