package fr.tse.startuppoc.project.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Entity
@Data
public class TimeDay {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Date date;
	
	//@Min((long) 0.125)
	//@Max((long) 1)
	private float granularity;
	
	@ManyToOne // Each TimeDay have one User but one User can have many TimeDay
	private User user;
	
	@ManyToOne // Each TimeDay have one Project but one Project can have many TimeDay
	private Project project;
	
	public TimeDay() {
		this.date = new Date(System.currentTimeMillis());
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getGranularity() {
		return granularity;
	}

	public void setGranularity(float granularity) {
		this.granularity = granularity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}
}
