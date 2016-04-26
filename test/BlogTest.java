import java.util.ArrayList;
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

public class BlogTest extends UnitTest
{
  private User bob;
  private Post post1, post2;
private Comment comment1;
private Comment comment2;

  @BeforeClass
  public static void loadDB()
  {
    Fixtures.deleteAllModels();
  }

  @Before
  public void setup()
  {
    bob   = new User("bob", "jones", "bob@jones.com", "secret", 20, "irish");
    post1 = new Post("Post Title 1", "This is the first post content");
    post2 = new Post("Post Title 2", "This is the second post content");
    bob.save();
    post1.save();
    post2.save();
  }

  @After
  public void teardown()
  {
    bob.delete();
    post1.delete();
    post2.delete();
  }

  @Test
  public void testCreatePost()
  {
    bob.posts.add(post1);
    bob.save();

    User user = User.findByEmail("bob@jones.com");
    List<Post> posts = user.posts;
    assertEquals(1, posts.size());
    Post post = posts.get(0);
    assertEquals(post.title, "Post Title 1");
    assertEquals(post.content, "This is the first post content");
  }

  @Test
  public void testCreateMultiplePosts()
  {
    bob.posts.add(post1);
    bob.posts.add(post2);
    bob.save();

    User user = User.findByEmail("bob@jones.com");
    List<Post> posts = user.posts;
    assertEquals(2, posts.size());
    Post posta = posts.get(0);
    assertEquals(posta.title, "Post Title 1");
    assertEquals(posta.content, "This is the first post content");

    Post postb = posts.get(1);
    assertEquals(postb.title, "Post Title 2");
    assertEquals(postb.content, "This is the second post content");
  }
  
  @Test
  public void testDeletePost()
  {
    Post post3 = new Post("Post Title 3", "This is the third post content");
    post3.save();
    bob.posts.add(post3);
    bob.save();
    
    User user = User.findByEmail("bob@jones.com");
    assertEquals(1, user.posts.size());  
    Post post = user.posts.get(0);

    user.posts.remove(0);
    user.save();
    post.delete();
    
    User anotherUser = User.findByEmail("bob@jones.com");
    assertEquals(0, anotherUser.posts.size());   
   }  
  

  
  @Test
  public void testCreateComment()
  {
    bob.comment.add(comment1);
    bob.save();

    User user = User.findByEmail("bob@jones.com");
    List<Comment> comments = user.comment;
    assertEquals(1, comments.size());
    Comment comment = comments.get(0);
    assertEquals(comment.content, "This is the first comment content");
  }

  @Test
  public void testCreateMultipleComments()
  {
    bob.comment.add(comment1);
    bob.comment.add(comment2);
    bob.save();

    User user = User.findByEmail("bob@jones.com");
    List<Comment> comments = user.comment;
    assertEquals(2, comments.size());
    Comment commenta = comments.get(0);
    assertEquals(commenta.content, "This is the first comment content");

    Comment commentb = comments.get(1);
    assertEquals(commentb.content, "This is the second comment content");
  }
  
  @Test
  public void testDeleteComment()
  {
    Comment comment3 = new Comment(bob, "This is the third comment content", null);
    comment3.save();
    bob.comment.add(comment3);
    bob.save();
    
    User user = User.findByEmail("bob@jones.com");
    assertEquals(1, user.comment.size());  
    Comment comment = user.comment.get(0);

    user.comment.remove(0);
    user.save();
    comment.delete();
    
    User anotherUser = User.findByEmail("bob@jones.com");
    assertEquals(0, anotherUser.comment.size());   
   }  
}