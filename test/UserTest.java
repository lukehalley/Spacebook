import models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class UserTest extends UnitTest
{
  private User bob;
  
  @BeforeClass
  public static void loadDB()
  {
    Fixtures.deleteAllModels();
  }

//  @Before
//  public void setup()
//  {
//    bob = new User("bob", "jones", "bob@jones.com", "secret",  20, "irish");
//    bob.save();
//  }
  
  @After
  public void teardown()
  {
    bob.delete();
  }
  
  @Test
  public void testCreateUser()
  {
    User testUser = User.findByEmail("bob@jones.com");
    assertNotNull (testUser);
  }
  
  @Test
  public void testFindUser()
  {
    User alice = User.findByEmail("alice@jones.com");
    assertNull (alice);
  }  
}
