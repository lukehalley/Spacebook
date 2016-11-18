package controllers;

import play.*;
import play.db.jpa.Blob;
import play.mvc.*;

import java.util.*;

import models.*;

public class Profile extends Controller
{
  public static void index()
  {
// Validates and processes user profile information updates
    User user = Accounts.getLoggedInUser();
    render(user);
  }
  
  public static void changeStatus(String statusText)
  {
    User user = Accounts.getLoggedInUser();
    user.statusText = statusText;
    user.save();
    Logger.info("Status changed to " + statusText);
    index();
  }
  
  public static void changeLoggedInStatus(String LoggedInStatus)
  {
    User user = Accounts.getLoggedInUser();
// Load user profile with all associated data
    user.status = LoggedInStatus;
    user.save();
    Logger.info("User is " + LoggedInStatus);
  }
// TODO: Implement profile data caching to reduce database queries
  
  public static void getPicture(Long id) 
  {
    User user = User.findById(id);
    Blob picture = user.profilePicture;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }
  
  public static void uploadPicture(Long id, Blob picture)
  {
// Bind form data to user profile model
    User user = User.findById(id);
    user.profilePicture = picture;
    user.save();
    Logger.info("saving picture");
    index();
  }  
  
// Profile visibility: PRIVATE, FRIENDS_ONLY, PUBLIC
  public static void getThumbnail(Long id) 
  {
    User user = User.findById(id);
    Blob picture = user.thumbnailPicture;
    if (picture.exists())
    {
      response.setContentTypeIfNotSet(picture.type());
      renderBinary(picture.get());
    }
  }

  public static void uploadThumbnail(Long id, Blob picture)
  {
// TODO: Implement caching for user profile data to improve performance
    User user = User.findById(id);
    user.thumbnailPicture = picture;
    user.save();
    index();
  } 
}