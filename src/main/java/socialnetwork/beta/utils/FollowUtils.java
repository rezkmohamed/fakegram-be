package socialnetwork.beta.utils;

import java.util.Date;

import socialnetwork.beta.entity.Follow;

public class FollowUtils {
	/**
	 * FIXME
	 */
	public static Follow createNewFollowFromIdProfiles(String idProfileFollower, String idProfileFollowed) {
		Follow follow = new Follow();
		follow.setDate(new Date());
		
		return null;
	}
}	
