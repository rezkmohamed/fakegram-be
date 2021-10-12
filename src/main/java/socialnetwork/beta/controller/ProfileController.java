package socialnetwork.beta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import socialnetwork.beta.dto.ProfileDTO;
import socialnetwork.beta.service.ProfileService;
import socialnetwork.beta.utils.RequestUtils;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
	@Autowired
	private ProfileService profileService;
	@Autowired
	private RequestUtils requestUtils;
	
	@GetMapping("/search/{nameLike}")
	public ResponseEntity<List<ProfileDTO>> getSearchProfiles(@PathVariable String nameLike){
		List<ProfileDTO> profiles = profileService.searchProfilesByName(nameLike);
		if(profiles != null) {
			return new ResponseEntity<>(profiles, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/liked/{idPost}")
	public ResponseEntity<List<ProfileDTO>> getProfilesLikedPost(@PathVariable String idPost){
		List<ProfileDTO> profiles = profileService.findProfilesLikesPost(idPost);
		if(profiles != null) {
			return new ResponseEntity<>(profiles, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/followers/{idProfile}")
	public ResponseEntity<List<ProfileDTO>> getFollowersForProfile(@PathVariable String idProfile) {
		List<ProfileDTO> profiles = profileService.findFollowersProfile(idProfile);
		if(profiles != null) {
			return new ResponseEntity<>(profiles, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/following/{idProfile}")
	public ResponseEntity<List<ProfileDTO>> getFollowingForProfile(@PathVariable String idProfile) {
		List<ProfileDTO> profiles = profileService.findFollowingProfile(idProfile);
		if(profiles != null) {
			return new ResponseEntity<>(profiles, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);		
	}
	
	@GetMapping("/logged")
	public ResponseEntity<ProfileDTO> getProfileLogged(HttpServletRequest request) {
		String idProfile = requestUtils.idProfileFromToken(request);
		if(idProfile == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		ProfileDTO profile = profileService.findProfileById(idProfile);
		
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}
	
	@GetMapping("/{idProfile}")
	public ResponseEntity<ProfileDTO> getProfileById(@PathVariable String idProfile){
		ProfileDTO profile = profileService.findProfileById(idProfile);
		if(profile != null) {
			return new ResponseEntity<>(profile, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * TODO
	 * @param profileDTO
	 * @param request
	 * @return
	 */
	@PutMapping("/updatebasics")
	public ResponseEntity<Boolean> updateGeneralData(@RequestBody ProfileDTO profileDTO, HttpServletRequest request){
		String idProfile = requestUtils.idProfileFromToken(request);
		profileDTO.setId(idProfile);
		if(profileService.updateProfile(profileDTO)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/newpass/{newPass}")
	public ResponseEntity<Boolean> updatePassword(@RequestBody ProfileDTO profileDTO, @PathVariable String newPass, HttpServletRequest request){
		
		
		return null;
	}

}
