package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.PostDTO;
import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.dto.UserDTO;
import socialnetwork.beta.entity.Post;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.repo.PostRepo;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.utils.ImgUtils;
import socialnetwork.beta.utils.PostUtils;
import socialnetwork.beta.utils.ProfileUtils;

@Service
public class ProfileServiceImpl implements ProfileService {
//	@Autowired
//	private ImgUtils imgUtils;
	@Autowired
	private ProfileRepo profileRepo;
	@Autowired
	private PostRepo postRepo;

	@Override
	@Transactional
	public List<ProfileDTO> findProfilesLikesPost(String idPost) {
		List<Profile> profiles = profileRepo.findProfilesLikesPost(idPost);
		
		return ProfileUtils.profileToDTO(profiles);
	}

	@Override
	@Transactional
	public List<ProfileDTO> findFollowersProfile(String idProfile) {
		List<Profile> profiles = profileRepo.findFollowersProfile(idProfile);
		
		return ProfileUtils.profileToDTO(profiles);
	}

	@Override
	@Transactional
	public List<ProfileDTO> findFollowingProfile(String idProfile) {
		List<Profile> profiles = profileRepo.findFollowingProfile(idProfile);
		
		return ProfileUtils.profileToDTO(profiles);
	}

	@Override
	@Transactional
	public List<ProfileDTO> searchProfilesByName(String profileName) {
		List<Profile> profiles = profileRepo.findProfilesByName(profileName);
		
		return ProfileUtils.profileToDTO(profiles);
	}

	@Override
	@Transactional
	public ProfileDTO findProfileById(String idProfile) {
		Profile profile = profileRepo.findProfile(idProfile);
		if(profile != null) {
			List<Post> posts = postRepo.findPostsProfilePage(idProfile);
			List<PostDTO> postsDTO = PostUtils.postToDTO(posts);
			ProfileDTO profileDTO = ProfileUtils.profileToDTO(profile);
			profileDTO.setPosts(postsDTO);
			profileDTO.setFollowersCounter(profile.getFollowers().size());
			profileDTO.setFollowingCounter(profile.getFollowing().size());
			return profileDTO;
		}
		
		return null;
	}

	@Override
	@Transactional
	public boolean saveProfile(ProfileDTO profile) {
		Profile profileCheckIfEmailExists = profileRepo.findProfileByEmail(profile.getEmail());
		if(profileCheckIfEmailExists != null) {
			return false;
		}
		Profile profileToSave = ProfileUtils.prepareProfileToRegister(profile);
		profileRepo.saveProfile(profileToSave);
		
		return true;
	}

	@Override
	@Transactional
	public boolean updateProfile(ProfileDTO profileDTO) {
		Profile profileToUpdate = ProfileUtils.prepareProfileToRegister(profileDTO);
		profileRepo.updateProfile(profileToUpdate);
		
		return true;
	}

	@Override
	@Transactional
	public boolean uploadProfilePic(MultipartFile file, ProfileDTO profile) {
		
		
		
		
		
		
		return false;
	}

	@Override
	@Transactional
	public UserDTO getProfileByEmailAndPassword(String email, String password) {
		Profile profile = profileRepo.findProfileByEmailAndPassword(email, password);
		if(profile == null) {
			return null;
		}
		
		return ProfileUtils.ProfileToUserDTO(profile);
	}

}
