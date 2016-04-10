package models;

import javax.persistence.*;
import play.db.jpa.Model;

@Entity
public class Friendship extends Model
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
}