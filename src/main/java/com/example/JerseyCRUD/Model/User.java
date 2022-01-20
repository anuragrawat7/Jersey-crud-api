package com.example.JerseyCRUD.Model;

public class User {
	
	private int userId;
	private String Name;
	private String City;
	private int Age;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public int getAge() {
		return Age;
	}
	public void setAge(int age) {
		Age = age;
	}
	public User(int userId, String name, String city, int age) {
		super();
		this.userId = userId;
		Name = name;
		City = city;
		Age = age;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Model [userId=" + userId + ", Name=" + Name + ", City=" + City + ", Age=" + Age + "]";
	}
	
}
