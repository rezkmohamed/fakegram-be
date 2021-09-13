package socialnetwork.beta.utils;

import java.util.ArrayList;
import java.util.List;

import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.entity.Profile;


public class ProfileUtils {
	public static ProfileDTO profileToDTO(Profile profile) {
		ProfileDTO ris = new ProfileDTO(profile.getIdProfile(),
										profile.getName(),
										profile.getNickname(),
										profile.getBio(),
										profile.getProPic(),
										profile.getEmail());
		
		return ris;
	}
	
	public static List<ProfileDTO> profileToDTO(List<Profile> profile){
		List<ProfileDTO> ris = new ArrayList<>();
		for(Profile p : profile) {
			ris.add(profileToDTO(p));
		}
		
		return ris;
	}
	
	public static Profile DTOProfileToProfileEntity(ProfileDTO profileDTO) {
		Profile ris = new Profile(profileDTO.getName(), profileDTO.getNickname(), profileDTO.getBio(), profileDTO.getProPic(), profileDTO.getEmail());
		
		return ris;
	}
}
