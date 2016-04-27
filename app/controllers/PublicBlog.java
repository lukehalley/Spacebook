package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class PublicBlog extends Controller
{
  public static void visit(Long id)
  {
    User user = User.findById(id);
    Logger.info("Just visiting the blog for " + user.firstName + ' ' + user.lastName);
    render(user);
  }

}