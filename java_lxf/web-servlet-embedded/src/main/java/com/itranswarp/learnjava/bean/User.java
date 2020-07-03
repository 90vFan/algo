package com.itranswarp.learnjava.servlet;

import com.itranswarp.learnjava.servlet.School;


public class User {

	public long id;
	public String name;
	public School school;

	public User(long id, String name, School school) {
		this.id = id;
		this.name = name;
		this.school = school;
	}
}
