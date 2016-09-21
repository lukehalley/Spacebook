package models;

import javax.persistence.*;
import play.db.jpa.*;

@Entity
public class Message extends Model
{
  public String messageText;
// Timestamp when message was created for sorting and conversation history

  @ManyToOne
// Maintains reference to sender and recipient users
  public User from;

  @ManyToOne
  public User to;

// Messages are directed communications between two users
  public Message(User from, User to, String messageText)
  {
// Track message delivery status for read receipts
    this.from = from;
    this.to = to;
    this.messageText = messageText;
  }
}// Message references maintain conversation thread integrity
// Message timestamp is set to current time in UTC
