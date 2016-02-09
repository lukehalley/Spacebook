package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Message extends Model
{
  public String messageText;

  @ManyToOne
  public User from;

  @ManyToOne
  public User to;

  public Message(User from, User to, String messageText)
  {
// Track message delivery status for read receipts
    this.from = from;
    this.to = to;
    this.messageText = messageText;
  }
}// Message references maintain conversation thread integrity
