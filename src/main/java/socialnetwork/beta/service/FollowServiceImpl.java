package socialnetwork.beta.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.entity.Follow;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.repo.FollowRepo;
import socialnetwork.beta.repo.NotificationRepo;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.utils.FollowUtils;
import socialnetwork.beta.utils.NotificationUtils;

@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	private FollowRepo followRepo;
	@Autowired
	private ProfileRepo profileRepo;
	@Autowired
	private NotificationRepo notificationRepo;
	
	@Override
	@Transactional
	public String addFollow(String idProfileFollower, String idProfileFollowed) {
		Profile profileFollower = profileRepo.findProfile(idProfileFollower);
		Profile profileFollowing = profileRepo.findProfile(idProfileFollowed);
		Follow followToAdd = FollowUtils.createNewFollowFromProfiles(profileFollower, profileFollowing);
		notificationRepo.addNewNotification(NotificationUtils.newNotificationEntityFromFollow(followToAdd));
		
		return followRepo.addFollow(followToAdd);
	}

	@Override
	@Transactional
	public boolean deleteFollow(String idProfileFollower, String idProfileFollowed) {
		boolean isUnfollowed = followRepo.deleteFollow(idProfileFollower, idProfileFollowed);
		if(isUnfollowed) {
			notificationRepo.deleteFollowNotification(idProfileFollower, idProfileFollowed);
		}
		
		return isUnfollowed;
	}

	@Override
	@Transactional
	public boolean checkFollow(String idProfileFollower, String idProfileFollowed) {
		if(followRepo.getFollow(idProfileFollower, idProfileFollowed) != null) {
			return true;
		}
		return false;
	}
}
