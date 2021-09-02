package socialnetwork.beta.repo;

import java.util.List;

import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.entity.Profile;

public interface ProfileRepo {
	public List<Profile> findProfilesLikesPost(String idPost);
	
	public List<Profile> findFollowersProfile(String idProfile);
	
	public List<Profile> findFollowingProfile(String idProfile);
	
	public Profile findProfileByEmail(String email);
	
	public Profile findProfile(String idProfile);
	
	public void saveProfile(Profile profile);
	
	public void updateProfile(Profile profile);
}
