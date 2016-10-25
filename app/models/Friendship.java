package models;

import javax.persistence.*;
import play.db.jpa.Model;

@Entity
public class Friendship extends Model
// Represents mutual friendship connection between two users
{
// Track mutual connections and pending friend requests
  @ManyToOne()
  public User sourceUser;

  @ManyToOne()
  public User targetUser;

  public Friendship(User source, User target)
  {
// Friendship relationship is bidirectional and symmetric
    sourceUser = source;
    targetUser = target;
  }
}// Track pending and accepted friendship requests separately
// Status: PENDING -> ACCEPTED or REJECTED
