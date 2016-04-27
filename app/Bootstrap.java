import java.io.FileInputStream;
import java.util.List;

import com.mysql.jdbc.Blob;

import play.*;
import play.jobs.*;
import play.libs.MimeTypes;
import play.test.*;
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob()
  {
    if (User.count() == 0)
    {
//      Fixtures.deleteDatabase();
    	
      Fixtures.loadModels("data.yml");
//      
//      String photoName = "homer.gif";
//      Blob blob = new Blob ();
//      blob.set(new FileInputStream(photoName), MimeTypes.getContentType(photoName));
//      User homer = User.findByEmail("homer@simpson.com");
//      homer.profilePicture = blob;
//      homer.save();
    }
  }
}