package com.example.Employee.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {

	private long id;
	private String userName;
	private String password;
	private String Role;
	
	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public Users() {
		
	}
	
	public Users(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(long id) {
		this.id = id;
	}
		
}

