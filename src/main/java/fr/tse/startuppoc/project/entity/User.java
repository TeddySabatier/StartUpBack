package fr.tse.startuppoc.project.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

import fr.tse.startuppoc.project.repository.UserTypeRepository;
import fr.tse.startuppoc.project.utils.Constants;
import lombok.Data;

@Entity
@Data
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String firstname;
	
	private String lastname;
	
	private String login;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	@ManyToOne // Each User have one Type but one Type can have many User
	private UserType type;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<User> developers;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Set<User> getDevelopers() {
		return developers;
	}

	public void setDevelopers(Set<User> developers) {
		this.developers = developers;
	}

	public User() {
		this.type = new UserType(Constants.ID_USER_TYPE_DEV, Constants.NAME_USER_TYPE_DEV);
		this.developers= new HashSet<User>();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	public User(Long id, String firstname, String lastname, String login, String password) {
		super();
		System.out.println(login);
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		
		
	}
	

}
