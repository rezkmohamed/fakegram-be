package socialnetwork.beta.repo;

import java.util.List;

import socialnetwork.beta.entity.Post;

public interface PostRepo {
	public List<Post> findAllPosts();
	
	public List<Post> findPostsProfilePage(String idProfile);
	
	public Post findPostById(String idPost);
	
	public String savePost(Post post);
	
	public boolean deletePostById(String idPost);
}
