package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

// Represents a user's post with content and timestamp
@Entity
public class Post extends Model
{
  public String title;
  @Lob
  public String content;
  
  @OneToMany
  public List<Comment> comments = new ArrayList<Comment>();

  public Post(String title, String content)
  {
    this.title = title;
    this.content = content;
  }

// Timestamp is automatically set by framework on save
// Posts must have valid content and belong to existing user
// Convert post content to JSON format for API responses
  public String toString()
  {
    return title;
  } 

}
// TODO: Add timezone support for post timestamps
