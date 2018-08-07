package ca.mcgill.ecse321.TreePle.dto;

public class UserDto {

	private String name;
	private String email;
	private String userID;
	
	public UserDto() {}
	
	public UserDto(String aName, String aEmail, String aUserID) {
		name = aName;
	    email = aEmail;
	    userID = aUserID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}
	
}
