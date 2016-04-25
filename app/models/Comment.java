package models;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class Comment extends Model {
	@Lob
	public String content;
	public Date postDate;

	@ManyToOne
	public User fromUser;

	public Comment(User from, String content, Date postDate) {
		this.fromUser = from;
		this.content = content;
		this.postDate = postDate;
	}

}
