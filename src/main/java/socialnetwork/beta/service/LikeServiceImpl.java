package socialnetwork.beta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.LikeDTO;
import socialnetwork.beta.entity.Post;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.repo.LikeRepo;
import socialnetwork.beta.repo.PostRepo;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.utils.LikeUtils;

@Service
public class LikeServiceImpl implements LikeService {
	@Autowired
	private LikeRepo likeRepo;
	@Autowired 
	private ProfileRepo profileRepo;
	@Autowired
	private PostRepo postRepo;

	@Override
	@Transactional
	public String addLike(LikeDTO likeDTO) {
		Post post = postRepo.findPostById(likeDTO.getIdPost());
		Profile profile = profileRepo.findProfile(likeDTO.getIdProfile());
		
		return likeRepo.addLike(LikeUtils.DTOLikeToLikeEntity(likeDTO, profile, post));
	}

	@Override
	@Transactional
	public boolean deleteLike(String idProfile, String idPost) {
		if(likeRepo.deleteLike(idProfile, idPost)) {
			return true;
		}
		
		return false;
	}

	@Override
	@Transactional
	public boolean getLikeByProfileAndPost(String idProfile, String idPost) {
		if(likeRepo.getLikeByProfileAndPost(idProfile, idPost) != null) {
			return true;
		}
		
		return false;
	}
	
	

}
