package com.imooc.object;

public class User {
	
	private String name;
	
	private Integer age;
	
	private ContactInfo contactInfo;
	
	

	public ContactInfo getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", contactInfo=" + contactInfo + "]";
	}

	
	
	
}
