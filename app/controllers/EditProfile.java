package controllers;

import play.*;
import play.mvc.*;
import java.util.*;
import models.*;

public class EditProfile extends Controller
{
  public static void change (String firstName,   String lastName, int    age, 
                             String nationality, String email,    String password, String password2)
  {
    User user = Accounts.getLoggedInUser();
    user.firstName = firstName;
    user.lastName = lastName;
    user.email = email;
    user.nationality = nationality;
    user.age = age;
    user.password = password;
    user.save();
    Profile.index();
  }

  public static void index()
  {
    User user = Accounts.getLoggedInUser();
    render(user);
  }
}