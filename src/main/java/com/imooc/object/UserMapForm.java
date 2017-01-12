package com.imooc.object;

import java.util.Map;

public class UserMapForm {

	private Map<String,User> userMap;

	public Map<String, User> getUserMap() {
		return userMap;
	}

	public void setUserMap(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	@Override
	public String toString() {
		return "UserMapForm [userMap=" + userMap + "]";
	}

	
	
}
