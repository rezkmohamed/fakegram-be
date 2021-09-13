package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.utils.ImgUtils;
import socialnetwork.beta.utils.ProfileUtils;

@Service
public class ProfileServiceImpl implements ProfileService {
	@Autowired
	private ImgUtils imgUtils;
	@Autowired
	private ProfileRepo profileRepo;

	@Override
	public List<ProfileDTO> findProfilesLikesPost(String idPost) {
		List<Profile> profiles = profileRepo.findProfilesLikesPost(idPost);
		
		return ProfileUtils.profileToDTO(profiles);
	}

	@Override
	public List<ProfileDTO> findFollowersProfile(String idProfile) {
		List<Profile> profiles = profileRepo.findFollowersProfile(idProfile);
		
		return ProfileUtils.profileToDTO(profiles);
	}

	@Override
	public List<ProfileDTO> findFollowingProfile(String idProfile) {
		List<Profile> profiles = profileRepo.findFollowingProfile(idProfile);
		
		return ProfileUtils.profileToDTO(profiles);
	}

	@Override
	public List<ProfileDTO> searchProfilesByName(String profileName, int startingIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProfileDTO findProfileById(String idProfile) {
		Profile profile = profileRepo.findProfile(idProfile);
		if(profile != null) {
			return ProfileUtils.profileToDTO(profile);
		}
		
		return null;
	}

	@Override
	public boolean saveProfile(ProfileDTO profile) {
		Profile profileToSave = ProfileUtils.DTOProfileToProfileEntity(profile);
		profileRepo.saveProfile(profileToSave);
		
		return true;
	}

	@Override
	public boolean updateProfile(ProfileDTO profileDTO) {
		Profile profileToUpdate = ProfileUtils.DTOProfileToProfileEntity(profileDTO);
		profileRepo.updateProfile(profileToUpdate);
		
		return true;
	}

	@Override
	public boolean uploadProfilePic(MultipartFile file, ProfileDTO profile) {
		
		
		
		
		
		
		return false;
	}

}
