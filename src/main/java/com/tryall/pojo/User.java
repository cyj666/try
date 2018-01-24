package com.tryall.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.solr.client.solrj.beans.Field;
import org.apache.xmlbeans.impl.xb.ltgfmt.FileDesc.Role;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class User implements Serializable{

	@Field("id")
	private int userId;
	
	@Field("name")
	@NotBlank(message="不能为空")
	private String username;
	
	@NotBlank(message="不能为空")
	private String password;
	
	private Integer status;
	
	private String passwordSalt;
	
	private boolean locked;
	
	private Set<Role> roleSet = new HashSet<Role>();
	 
	/*private Set<String> roles = new HashSet<>();
	
	private Set<String> permissions = new HashSet<>();
	
	
	public Set<String> getPermissions() {
		return permissions;
	}
	
	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}
	
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}*/
	
	//private enum color{};

	public User() {
		// TODO Auto-generated constructor stub
		
	}


	


	public int getUserId() {
		return userId;
	}





	public void setUserId(int userId) {
		this.userId = userId;
	}





	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPasswordSalt() {
		return passwordSalt;
	}


	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	

	


	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", passwordSalt="
				+ passwordSalt + ", roleSet=" + roleSet + "]";
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}





	public boolean getLocked() {
		return locked;
	}





	public void setLocked(boolean locked) {
		this.locked = locked;
	}





	public Integer getStatus() {
		return status;
	}





	public void setStatus(Integer status) {
		this.status = status;
	}




	public Set<Role> getRoleSet() {
		return roleSet;
	}


	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}
	

	

}
