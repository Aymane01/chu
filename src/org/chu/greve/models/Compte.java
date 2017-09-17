package org.chu.greve.models;

public class Compte {
	private int id;
	private String username;
	private String password;
	
	public Compte() {
		// TODO Auto-generated constructor stub
	}
	
	public Compte(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
