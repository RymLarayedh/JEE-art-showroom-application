package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("COMMENT")
public class Comment  extends Feedback implements Serializable{
	
	private String bodyComment;
	private Date dateComment;
	private static final long serialVersionUID = 1L;
	@Override
	public String toString() {
		return "Comment [bodyComment=" + bodyComment + ", dateComment=" + dateComment + "]";
	}
	public String getBodyComment() {
		return bodyComment;
	}
	public void setBodyComment(String bodyComment) {
		this.bodyComment = bodyComment;
	}
	public Date getDateComment() {
		return dateComment;
	}
	public void setDateComment(Date dateComment) {
		this.dateComment = dateComment;
	}
	public Comment() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bodyComment == null) ? 0 : bodyComment.hashCode());
		result = prime * result + ((dateComment == null) ? 0 : dateComment.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comment other = (Comment) obj;
		if (bodyComment == null) {
			if (other.bodyComment != null)
				return false;
		} else if (!bodyComment.equals(other.bodyComment))
			return false;
		if (dateComment == null) {
			if (other.dateComment != null)
				return false;
		} else if (!dateComment.equals(other.dateComment))
			return false;
		return true;
	}
	
	
	
}
