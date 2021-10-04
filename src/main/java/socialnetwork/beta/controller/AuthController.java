package socialnetwork.beta.controller;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Jwts;
import socialnetwork.beta.dto.UserDTO;
import socialnetwork.beta.service.ProfileService;


@RestController("")
public class AuthController {
	@Autowired
	private ProfileService profileService;
	@Value("${signingKey}")
	private String signingKey;
	
	@PostMapping("/login")
	public ResponseEntity<UserDTO> login(@RequestBody UserDTO user){
		UserDTO checkUser = profileService.getProfileByEmailAndPassword(user.getEmail(), user.getPass());
		if(checkUser != null && checkUser.getEmail().equals(user.getEmail()) 
					&& checkUser.getPass().equals(user.getPass())) {
			HttpHeaders headers = new HttpHeaders();
        	HashMap<String, Object> addedValues = new HashMap<String, Object>();
        	addedValues.put("idUser", checkUser.getIdUser());
        	addedValues.put("nickname", checkUser.getNickname());
        	
        	String token = Jwts.builder()
        			.addClaims(addedValues)
        			.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis() + 4 * 120 * 60 * 1000))
					.signWith(SignatureAlgorithm.HS512, this.signingKey).compact();
        	headers.add("Authentication","Bearer " + token);
			
			return ResponseEntity.ok().headers(headers).build();
		}
		
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
