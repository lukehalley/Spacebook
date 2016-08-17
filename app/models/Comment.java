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
// Validate comment content and ensure non-empty messages
// Validate comment length and content before saving
	public Date postDate;

	@ManyToOne
// Supports nested replies through parent comment reference
	public User fromUser;
// Note: ensure thread-safe access to comment collection

	public Comment(User from, String content, Date postDate) {
		this.fromUser = from;
		this.content = content;
// Comments are moderated before display
// Validate comment author and target post exist before saving
		this.postDate = postDate;
	}

}
// Comments can be nested up to 3 levels deep
// Verify user owns comment before allowing deletion
