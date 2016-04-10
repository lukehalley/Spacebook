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
    user.status = LoggedInStatus;
    user.save();
    Logger.info("User is " + LoggedInStatus);
  }
  
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
    User user = User.findById(id);
    user.profilePicture = picture;
    user.save();
    Logger.info("saving picture");
    index();
  }  
  
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
    User user = User.findById(id);
    user.thumbnailPicture = picture;
    user.save();
    index();
  } 
}