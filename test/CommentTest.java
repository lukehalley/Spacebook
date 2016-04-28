import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Comment;
import models.Message;
import models.Post;
import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;

public class CommentTest extends UnitTest {
	private User bob;
	private Post post1, post2;
	private Comment comment1, comment2;

	@BeforeClass
	public static void loadDB() {
		Fixtures.deleteAllModels();
	}

	@Before
	public void setup() {
		bob = new User("bob", "jones", "bob@jones.com", "secret", 20, "irish");
		post1 = new Post("Post Title 1", "This is the first post content");
		post2 = new Post("Post Title 2", "This is the second post content");
		bob.save();
		post1.save();
		post2.save();
	}

	@After
	public void teardown() {
		post1.delete();
		post2.delete();
		bob.delete();
	}


	@Test
	public void testCreateComment() {
		comment1 = new Comment(bob, "This is the first comment content", new Date());
		
		bob.posts.add(post1);
		post1.comments.add(comment1);
		bob.save();

		User user = User.findByEmail("bob@jones.com");
		List<Post> posts = user.posts;
		List<Comment> comments = posts.get(0).comments;
		assertEquals(1, comments.size());
		Comment comment = comments.get(0);
		assertEquals(comment.content, "This is the first comment content");
		
		bob.posts.remove(post1);
		post1.comments.remove(comment1);
		bob.save();
	}

	@Test
	public void testCreateMultipleComments() {
		
		comment1 = new Comment(bob, "This is the first comment content", new Date());
		comment2 = new Comment(bob, "This is the second comment content", new Date());
		
		
		bob.posts.add(post1);
		bob.posts.add(post2);
		post1.comments.add(comment1);
		post1.comments.add(comment2);
		bob.save();
		
		User user = User.findByEmail("bob@jones.com");
		List<Post> posts = user.posts;
		List<Comment> comments = posts.get(0).comments;
		assertEquals(2, comments.size());
		
		Comment commenta = comments.get(0);
		assertEquals(commenta.content, "This is the first comment content");
		
		Comment commentb = comments.get(1);
		assertEquals(commentb.content, "This is the second comment content");
		
		bob.posts.remove(post1);
		bob.posts.remove(post2);
		post1.comments.remove(comment1);
		post2.comments.remove(comment2);
		bob.save();
		
	}

	@Test
	public void testDeleteComment() {
		
		comment1 = new Comment(bob, "This is the first comment content", new Date());
		
		bob.posts.add(post1);
		post1.comments.add(comment1);
		bob.save();

		User user = User.findByEmail("bob@jones.com");
		List<Post> posts = user.posts;
		List<Comment> comments = posts.get(0).comments;
		assertEquals(1, comments.size());
		
		Comment comment = comments.get(0);
		assertEquals(comment.content, "This is the first comment content");

		user.posts.get(0).comments.remove(0);
		bob.save();

		assertEquals(0, comments.size());
		
		bob.posts.remove(post1);
		post1.comments.remove(comment1);
		bob.save();
		
	}
}