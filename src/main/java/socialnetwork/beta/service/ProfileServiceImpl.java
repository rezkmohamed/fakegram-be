package socialnetwork.beta.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import socialnetwork.beta.utils.PostUtils;
import socialnetwork.beta.utils.ProfileUtils;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ProfileRepo profileRepo;
	@Autowired
	private PostRepo postRepo;
	@Value("${basePathFileSystem}")
	private String basePathFileSystem;

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
			profileDTO.setPostsCounter(postsDTO.size());
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
		Profile profileToUpdate = profileRepo.findProfile(profileDTO.getId());
		if(profileToUpdate == null) {
			return false;
		}
		if(profileDTO.getBio() != null) {
			profileToUpdate.setBio(profileDTO.getBio());
		}
		if(profileDTO.getEmail() != null) {
			profileToUpdate.setEmail(profileDTO.getEmail());
		}
		if(profileDTO.getName() != null) {
			profileToUpdate.setName(profileDTO.getName());
		}
		if(profileDTO.getNickname() != null) {
			profileToUpdate.setNickname(profileDTO.getNickname());
		}
		
		profileRepo.updateProfile(profileToUpdate);
		
		return true;
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

	@Override
	@Transactional
	public boolean updatePassword(String idProfile, String oldPassword, String newPassword) {
		Profile profile = profileRepo.findProfile(idProfile);
		if(!profile.getPassword().equals(oldPassword)) {
			return false;
		}
		profile.setPassword(newPassword);
		profileRepo.updateProfile(profile);

		return true;
	}

	@Override
	@Transactional
	public boolean updateProfilePic(String idProfile, String newImg) {
		Profile profile = profileRepo.findProfile(idProfile);
		profile.setProPic(newImg);
		profileRepo.updateProfile(profile);
		
		return true;
	}

	@Override
	@Transactional
	public boolean uploadProfilePicture(MultipartFile file, String idProfile) {
		String filename = file.getOriginalFilename();
		String extension  = filename.substring(filename.lastIndexOf(".") + 1);
		
		if(extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("jpg")) {
			String newProfilePic = UUID.randomUUID().toString()+ "." + extension;
			try {
				file.transferTo(new File(basePathFileSystem + newProfilePic));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Profile profile = profileRepo.findProfile(idProfile);
			profile.setProPic(newProfilePic);
			profileRepo.saveProfile(profile);
			return true;
		}
		
		
		return false;
	}

}
