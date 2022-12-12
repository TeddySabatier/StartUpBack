package fr.tse.startuppoc.project.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
public class TimeDay {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private Date date;
	
	@Min((long) 0.125)
	@Max((long)1)
	private float granularity;

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
	
	
}
