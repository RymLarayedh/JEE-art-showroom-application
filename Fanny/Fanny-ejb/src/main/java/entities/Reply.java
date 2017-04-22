package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reply
 *
 */
@Entity

public class Reply implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idReply;
	private Date AddedAt;
	@ManyToOne(targetEntity=User.class)
	private User AddedBy;
	@ManyToOne(targetEntity=Post.class)
	private Post post;
	private String text;
	private static final long serialVersionUID = 1L;

	public Reply() {
		super();
	}

	public int getIdReply() {
		return idReply;
	}

	public void setIdReply(int idReply) {
		this.idReply = idReply;
	}

	public Date getAddedAt() {
		return AddedAt;
	}

	public void setAddedAt(Date addedAt) {
		AddedAt = addedAt;
	}

	public User getAddedBy() {
		return AddedBy;
	}

	public void setAddedBy(User addedBy) {
		AddedBy = addedBy;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Reply [idReply=" + idReply + ", AddedAt=" + AddedAt + ", AddedBy=" + AddedBy + ", post=" + post
				+ ", text=" + text + "]";
	}

	
	
	
   
}
