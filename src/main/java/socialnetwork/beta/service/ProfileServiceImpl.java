package socialnetwork.beta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.dto.UserDTO;
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
			return ProfileUtils.profileToDTO(profile);
		}
		
		return null;
	}

	@Override
	@Transactional
	public boolean saveProfile(ProfileDTO profile) {
		Profile profileToSave = ProfileUtils.DTOProfileToProfileEntity(profile);
		profileRepo.saveProfile(profileToSave);
		
		return true;
	}

	@Override
	@Transactional
	public boolean updateProfile(ProfileDTO profileDTO) {
		Profile profileToUpdate = ProfileUtils.DTOProfileToProfileEntity(profileDTO);
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
