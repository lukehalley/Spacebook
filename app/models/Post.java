package models;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

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

  public String toString()
  {
    return title;
  } 

}
