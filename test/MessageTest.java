import java.util.List;
import models.Message;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import play.test.Fixtures;
import play.test.UnitTest;

public class MessageTest extends UnitTest
{
  private User mary, joan;

  @BeforeClass
  public static void loadDB()
  {
    Fixtures.deleteAllModels();
  }

//  @Before
//  public void setup()
//  {
//    mary = new User("mary", "colllins", "mary@collins.com", "secret", 20, "irish");
//    mary.save();
//
//    joan = new User("joan", "colllins", "joan@collins.com", "secret", 20, "irish");
//    joan.save();
//  }

  @After
  public void teardown()
  {
    mary.delete();
    joan.delete();
  }

  @Test
  public void testCreateMessage()
  {
    Message msg = new Message(mary, joan, "Hi there - how are you");
    msg.save();

    List<Message> joansMessages = Message.find("byTo", joan).fetch();
    assertEquals(joansMessages.size(), 1);

    msg.delete();
  }

  @Test
  public void testNoMessagese()
  {
    List<Message> joansMessages = Message.find("byTo", mary).fetch();
    assertEquals(joansMessages.size(), 0);
  }

  @Test
  public void testMultipleMessages()
  {
    Message msg1 = new Message(mary, joan, "Hi there - how are you");
    msg1.save();
    Message msg2 = new Message(mary, joan, "Where are you now?");
    msg2.save();

    List<Message> joansMessages = Message.find("byTo", joan).fetch();
    assertEquals(joansMessages.size(), 2);
    Message message1 = joansMessages.get(0);
    assertEquals(message1.messageText, "Hi there - how are you");
    Message message2 = joansMessages.get(1);
    assertEquals(message2.messageText, "Where are you now?");

    msg1.delete();
    msg2.delete();

  }

}