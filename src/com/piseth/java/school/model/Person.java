package com.piseth.java.school.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	private int id;
	private String name;
	private Gender gender;
	private int age;
	private List<Pet> pets;

	public Person(String name, Gender gender, int age, List<Pet> pets) {
		super();
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.pets = pets;
	}

	

}
