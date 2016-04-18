package models;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;


@Entity
public class Comment extends Model
{
  @Lob
  public String content;
  
  @ManyToOne
  public User fromUser;

  public Comment(User from, String content)
  {
    this.content = content;
    this.fromUser = from;
  }

}
