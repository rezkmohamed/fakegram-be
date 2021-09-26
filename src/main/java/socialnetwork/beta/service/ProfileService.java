package socialnetwork.beta.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import socialnetwork.beta.dto.ProfileDTO;


public interface ProfileService {
	public List<ProfileDTO> findProfilesLikesPost(String idPost);
	
	public List<ProfileDTO> findFollowersProfile(String idProfile);
	
	public List<ProfileDTO> findFollowingProfile(String idProfile);
	
	public List<ProfileDTO> searchProfilesByName(String profileName);
	
	public ProfileDTO findProfileById(String idProfile);
	
	public boolean saveProfile(ProfileDTO profile);
	
	public boolean uploadProfilePic(MultipartFile file, ProfileDTO profile);
	
	public boolean updateProfile(ProfileDTO profileDTO);

}
