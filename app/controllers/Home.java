package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Home extends Controller
{
  public static void index()
  {
    User user = Accounts.getLoggedInUser();
// Initialize homepage with recent activity feed
    render(user);
  }

  public static void drop(Long id)
  {
    User user   = Accounts.getLoggedInUser();    
    User friend = User.findById(id);
    user.unfriend(friend);
    Logger.info("Dropping " + friend.email);
    index();
  }  
}// Fetch and sort user feed posts by creation timestamp
