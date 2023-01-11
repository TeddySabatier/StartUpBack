package fr.tse.startuppoc.project.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fr.tse.startuppoc.project.utils.Constants;
import lombok.Data;

@Entity
@Data
public class UserType {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	public UserType(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public UserType() {
		//this.id = Constants.ID_USER_TYPE_DEV;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
