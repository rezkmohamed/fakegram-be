package socialnetwork.beta.utils;

import java.util.Date;

import socialnetwork.beta.entity.Follow;
import socialnetwork.beta.entity.Profile;

public class FollowUtils {
	/**
	 * FIXME
	 */
	public static Follow createNewFollowFromProfiles(Profile profileFollower, Profile profileFollowed) {
		Follow follow = new Follow();
		follow.setDate(new Date());
		follow.setFollower(profileFollower);
		follow.setFollowed(profileFollowed);
		
		return follow;
	}
}	
