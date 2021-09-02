package socialnetwork.beta.service;

import java.util.List;

import socialnetwork.beta.dto.ProfileDTO;

public class ProfileServiceImpl implements ProfileService {

	@Override
	public List<ProfileDTO> findProfilesLikesPost(String idPost) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProfileDTO> findFollowersProfile(String idProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProfileDTO> findFollowingProfile(String idProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProfileDTO> searchProfilesByName(String profileName, int startingIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileDTO findProfileById(String idProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveProfile(ProfileDTO profile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProfile(ProfileDTO profileDTO) {
		// TODO Auto-generated method stub
		return false;
	}

}
