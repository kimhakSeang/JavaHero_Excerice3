package com.piseth.java.school.model;

import java.util.List;

public class Person {
	private int id;
	private String name;
	private Gender gender;
	private int age;
	private List<Pet> pets;

	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String name, Gender gender, int age, List<Pet> pets) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.pets = pets;
	}

	@Override
	public String toString() {
		return "\t" + name + "\t\t" + gender + "\t" + age + "\t" + pets + "\t";
	}
	public void setId(int id ) {
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

}
