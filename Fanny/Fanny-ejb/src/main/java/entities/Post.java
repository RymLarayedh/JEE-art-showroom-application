package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Post
 *
 */
@Entity

public class Post implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPost;
	private Date AddedAt;
	private String Text;

	@ManyToOne(targetEntity=User.class)
	private User AddedBy;
	@ManyToOne(targetEntity=Topic.class)
	private Topic topic;
	@OneToMany(targetEntity=Reply.class,mappedBy="post",cascade={CascadeType.ALL},orphanRemoval=true)
	private List<Reply> listReply;
	private static final long serialVersionUID = 1L;

	public Post() {
		super();
	}   
	public int getIdPost() {
		return this.idPost;
	}

	@Override
	public String toString() {
		return "Post [idPost=" + idPost + ", AddedAt=" + AddedAt + ", Text=" + Text + ", AddedBy=" + AddedBy
				+ ", topic=" + topic + ", listReply=" + listReply + "]";
	}
	public void setIdPost(int idPost) {
		this.idPost = idPost;
	}
	public Date getAddedAt() {
		return AddedAt;
	}
	public void setAddedAt(Date addedAt) {
		AddedAt = addedAt;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public User getAddedBy() {
		return AddedBy;
	}
	public void setAddedBy(User addedBy) {
		AddedBy = addedBy;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public List<Reply> getListReply() {
		return listReply;
	}
	public void setListReply(List<Reply> listReply) {
		this.listReply = listReply;
	}
	
	
	
	
   
}
