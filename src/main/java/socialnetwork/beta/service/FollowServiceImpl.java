package socialnetwork.beta.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import socialnetwork.beta.entity.Follow;
import socialnetwork.beta.repo.FollowRepo;
import socialnetwork.beta.utils.FollowUtils;


@Service
public class FollowServiceImpl implements FollowService {
	@Autowired
	private FollowRepo followRepo;
	
	@Override
	@Transactional
	public String addFollow(String idProfileFollower, String idProfileFollowed) {
		Follow followToAdd = FollowUtils.createNewFollowFromIdProfiles(idProfileFollower, idProfileFollowed);
		
		return followRepo.addFollow(followToAdd);
	}

	@Override
	@Transactional
	public boolean deleteFollow(String idProfileFollower, String idProfileFollowed) {
		return followRepo.deleteFollow(idProfileFollower, idProfileFollowed);
	}

}
