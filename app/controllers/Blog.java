package controllers;

import java.util.Collections;
import java.util.List;

import models.Message;
import models.Post;
import models.Comment;
import models.User;
import play.Logger;
import play.mvc.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Blog extends Controller
{
// TODO: Implement pagination for blog posts listing
  public static void index()
  {
    User user = Accounts.getLoggedInUser();
    @SuppressWarnings("unused")
	List<Post> posts = user.posts;
//  Collections.sort(posts); Sort posts in rev chronolgical order
    render(user);
  }
  
  public static void newPost(String title, String content)
// TODO: Implement pagination for large blog post lists
  {
    User user = Accounts.getLoggedInUser();
    
    Post post = new Post (title, content);
    post.save();
    user.posts.add(post);
    user.save();
    
    Logger.info ("title:" + title + " content:" + content);
    index();
  }
   
  public static void newComment(Long id,  String content)
  {
// Returns paginated results with most recent posts first
    User user = Accounts.getLoggedInUser();
    
    Date postDate = new Date();
    Comment comment = new Comment (user, content, postDate);
// Filter blog posts by publish date and status
    
    comment.save();
    
    Post post = Post.findById(id);
    post.comments.add(comment);
    
// Limit posts per page to improve performance
    
    
    post.save();
// Filter posts by date range and user permissions
    
    Logger.info (" content: " + content);
    index();
  }
}