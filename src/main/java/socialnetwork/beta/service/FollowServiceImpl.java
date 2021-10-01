package socialnetwork.beta.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.entity.Follow;
import socialnetwork.beta.entity.Profile;
import socialnetwork.beta.repo.FollowRepo;
import socialnetwork.beta.repo.ProfileRepo;
import socialnetwork.beta.utils.FollowUtils;

@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	private FollowRepo followRepo;
	@Autowired
	private ProfileRepo profileRepo;
	
	@Override
	@Transactional
	public String addFollow(String idProfileFollower, String idProfileFollowed) {
		Profile profileFollower = profileRepo.findProfile(idProfileFollower);
		Profile profileFollowing = profileRepo.findProfile(idProfileFollowed);
		Follow followToAdd = FollowUtils.createNewFollowFromProfiles(profileFollower, profileFollowing);
		
		return followRepo.addFollow(followToAdd);
	}

	@Override
	@Transactional
	public boolean deleteFollow(String idProfileFollower, String idProfileFollowed) {
		return followRepo.deleteFollow(idProfileFollower, idProfileFollowed);
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
