package com.piseth.java.school;

import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.piseth.java.school.ServiceDao.DaoService;
import com.piseth.java.school.ServiceDao.DaoServiceImpl;
import com.piseth.java.school.model.Gender;
import com.piseth.java.school.model.Person;
import com.piseth.java.school.model.Pet;
import com.piseth.java.school.service.PersonService;
import com.piseth.java.school.service.PersonServiceImpl;

public class PersonApplication {
	
	private final PersonService personService = new PersonServiceImpl();
	private DaoService serviceDao= new DaoServiceImpl();
	private Connection connection = serviceDao.connection();

	public static void main(String[] args) {
		PersonApplication service= new PersonApplication();
		
		
		  // People that feed cat 
		  service.peopleWhoHaveCat();
		  
		  // Count people by gender 
		  service.showNumberOfPeopleByGender();
		  
		  //Find person like cat but don't like dog and has age between 18 to 21
		  service.showPersonLikeCat_UnlikeDog_Female_18to21();
		 
		  // Find people who feed pets
		  service.peopleWhoHavePetMoreThanOneType();
		  
		  //Do someone don't feet pet ?
		  service.showPersonDoesNotHavePet();
		
		  //Find gender that like cat most than other one
		  service.showGenderOfPeopleWhoLikeCatTheMost();
		  
	}
	
	public void showNumberOfPeopleByGender() {
		String query= "SELECT person_gender,count(person_gender) FROM person group by person_gender;";
		Map<Gender, Long> countByGender = personService.countNumberOfPeopleByGender(connection, query);
		System.out.println("Male   = "+countByGender.get(Gender.MALE));
		System.out.println("Female = "+countByGender.get(Gender.FEMALE));
	}
	
	public void peopleWhoHavePetMoreThanOneType() {
		String query = "SELECT * FROM person WHERE person_pet LIKE '%,%'";
		List<Person> people = personService.findByNumberOfPetMoreThan(connection, query);
		people.forEach(System.out::println);
	}
	
	public void peopleWhoHaveCat() {
		String query ="SELECT * FROM person WHERE person_pet LIKE '%Cat%'";
		List<Person> peopleFromDao = personService.getListPeopleFromDao(connection,query);
		peopleFromDao.forEach(s->System.out.println(s.getId()+"\t"+s.toString()));
	}
	
	public void showGenderOfPeopleWhoLikeCatTheMost() {
		String query = "SELECT \n"
				+ "person_gender,count(person_gender) AS num\n"
				+ "FROM person\n"
				+ "WHERE person_pet LIKE '%Cat%'\n"
				+ "GROUP BY person_gender";
		Gender gender = personService.findGenderLikeCatMost(connection, query);
		System.out.println("Gender That Like Cat Most is "+gender);
	}
	
	public void showPersonDoesNotHavePet() {
		String query = "SELECT * FROM person WHERE person_pet IS NULL";
		Boolean havePet = personService.doSomeoneDoNotFeedPet(connection, query);
		System.out.println(havePet);
	}
	
	public void youngestPerson() {
		String query ="SELECT * FROM person WHERE person_age=(SELECT MIN(person_age) FROM person)";
		Person p=personService.youngestPerson(connection, query);
		System.out.println(p.toString());
	}

	public void showPersonLikeCat_UnlikeDog_Female_18to21() {
		String query = "SELECT * FROM person WHERE person_pet LIKE '%Cat%'\n"
				+ "and person_pet NOT LIKE '%Dog%'\n"
				+ "and person_age BETWEEN 18 and 21";
		List<Person> people = personService.findPeople_LikeCat_UnlikeDog_Female_18to21(connection, query);
		people.forEach(System.out::println);
	}
	

}
