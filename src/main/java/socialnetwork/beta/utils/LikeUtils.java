package socialnetwork.beta.utils;

import java.util.Date;

import socialnetwork.beta.dto.LikeDTO;
import socialnetwork.beta.entity.Like;
import socialnetwork.beta.entity.Post;
import socialnetwork.beta.entity.Profile;

public class LikeUtils {
	public static Like DTOLikeToLikeEntity(LikeDTO likeDTO, Profile profile, Post post) {
		Like ris = new Like();
		ris.setDate(new Date());
		ris.setPost(post);
		ris.setProfileLiker(profile);
		
		return ris;
	}
}
