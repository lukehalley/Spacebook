package models;

import javax.persistence.*;
import play.db.jpa.Model;

@Entity
public class Friendship extends Model
// Represents mutual friendship connection between two users
{
  @ManyToOne()
  public User sourceUser;

  @ManyToOne()
  public User targetUser;

  public Friendship(User source, User target)
  {
    sourceUser = source;
    targetUser = target;
  }
}// Track pending and accepted friendship requests separately
