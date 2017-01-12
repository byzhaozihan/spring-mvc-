package com.imooc.object;

import java.util.LinkedHashSet;
import java.util.Set;

public class UserSetForm {

	private Set<User> userSet;
	
	private UserSetForm(){
		userSet = new LinkedHashSet<>();
		userSet.add(new User());
		userSet.add(new User());
		
	}

	public Set<User> getUserSet() {
		return userSet;
	}

	public void setUserSet(Set<User> userSet) {
		this.userSet = userSet;
	}

	@Override
	public String toString() {
		return "UserSetForm [userSet=" + userSet + "]";
	}
	
	
	
}
