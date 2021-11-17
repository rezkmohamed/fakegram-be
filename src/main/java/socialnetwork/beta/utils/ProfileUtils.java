package socialnetwork.beta.utils;

import java.util.ArrayList;
import java.util.List;

import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.dto.UserDTO;
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
	
	public static Profile prepareProfileToRegister(ProfileDTO profileDTO) {
		Profile ris = new Profile();
		ris.setEmail(profileDTO.getEmail());
		ris.setPassword(profileDTO.getPassword());
		ris.setName(profileDTO.getName());
		ris.setNickname(profileDTO.getNickname());
		
		return ris;
	}
	
	public static Profile DTOProfileToProfileEntity(ProfileDTO profileDTO) {
		Profile ris = new Profile(profileDTO.getName(), profileDTO.getNickname(), profileDTO.getBio(), profileDTO.getProPic(), profileDTO.getEmail());
		
		return ris;
	}
	
	public static UserDTO ProfileToUserDTO(Profile profile) {
		UserDTO ris = new UserDTO(profile.getIdProfile(), profile.getNickname(), profile.getEmail(), profile.getPassword());
		
		return ris;
	}
}
