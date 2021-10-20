package socialnetwork.beta.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.dto.UserDTO;


public interface ProfileService {
	public List<ProfileDTO> findProfilesLikesPost(String idPost);
	
	public List<ProfileDTO> findFollowersProfile(String idProfile);
	
	public List<ProfileDTO> findFollowingProfile(String idProfile);
	
	public List<ProfileDTO> searchProfilesByName(String profileName);
	
	public ProfileDTO findProfileById(String idProfile);
	
	public UserDTO getProfileByEmailAndPassword(String email, String password);
	
	public boolean saveProfile(ProfileDTO profile);
	
	public boolean updateProfilePic(String idProfile, String newImg);
	
	public boolean updateProfile(ProfileDTO profileDTO);
	
	public boolean updatePassword(String idProfile, String oldPassword, String newPassword);

}
