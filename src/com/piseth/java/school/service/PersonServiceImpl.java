package com.piseth.java.school.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.piseth.java.school.model.Gender;
import com.piseth.java.school.model.Person;
import com.piseth.java.school.model.Pet;

public class PersonServiceImpl implements PersonService{
	@Override
	public List<Person> getListPeopleFromDao(Connection connection,String query) {
		List<Person> people = new ArrayList<Person>();
		try {
			connection.setAutoCommit(false);
			Statement stm = connection.createStatement();
			ResultSet result = stm.executeQuery(query);
			while(result.next()) {
				int id = result.getInt("person_id");
				String name = result.getString("person_name");
				Gender gender = result.getString("person_gender").equals("MALE")? Gender.MALE:Gender.FEMALE;
				int age = result.getInt("person_age");
				String pet = result.getString("person_pet");
				List<Pet> ListPet = convertStringToListPet(pet);
				Person p = new Person(name,gender, age,ListPet);
				p.setId(id);
				people.add(p);
			}
			stm.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		
		return people;
	}
	public List<Pet> convertStringToListPet(String pet){
		List<Pet> ListPet=new ArrayList<>();
		if(pet!=null) {
			String[] pets = pet.split(","); 
			for(String p: pets) {
				if(p.toLowerCase().equals("cat")) {
					ListPet.add(Pet.CAT);
				}else if(p.toLowerCase().equals("dog")){
					ListPet.add(Pet.DOG);
				}else if(p.toLowerCase().equals("fish")) {
					ListPet.add(Pet.FISH);
				}else if (p.toLowerCase().equals("bird")) {
					ListPet.add(Pet.BIRD);
				}else {
					ListPet.add(null);
				}
			}
		}
		return ListPet;
	}
	@Override
	public Person getPersonFromDao(Connection connection, String query) {
		try {
			connection.setAutoCommit(false);
			Statement stm = connection.createStatement();
			ResultSet result = stm.executeQuery(query);
			while(result.next()) {
				int id = result.getInt("person_id");
				String name = result.getString("person_name");
				Gender gender = result.getString("person_gender").equals("MALE")? Gender.MALE:Gender.FEMALE;
				int age = result.getInt("person_age");
				String pet = result.getString("person_pet");
				System.out.println(pet);
				Person p = new Person(name,gender, age, null);
				p.setId(id);
				return p;
			}
			stm.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return new Person();
	}

	@Override
	public Map<Gender, Long> countNumberOfPeopleByGender(Connection connection,String query)  {
		   Map<Gender,Long> countGender= new HashMap<>();
		   try {
			Statement stat = connection.createStatement();
			ResultSet result = stat.executeQuery(query);
			while(result.next()) {
				countGender.put(result.getString("person_gender").equals("MALE")?Gender.MALE:Gender.FEMALE, result.getLong("count"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		   
		return countGender;
	}

	@Override
	public List<Person> findByNumberOfPetMoreThan(Connection connection, String query) {
		return getListPeopleFromDao(connection,query);
	}

	@Override
	public List<Person> findPeopleFeedCat(Connection connection, String query) {
		return getListPeopleFromDao(connection, query);
	}
	
	@Override
	public Gender findGenderLikeCatMost(Connection connection, String query) {
		   Map<Gender, Integer> map = new HashMap<Gender, Integer>();
		   try {
			Statement stat =connection.createStatement();
			ResultSet result=stat.executeQuery(query);
			while(result.next()) {
				Gender gender = result.getString("person_gender").equals("MALE")?Gender.MALE:Gender.FEMALE;
				Integer num = result.getInt("num");
				map.put(gender,num);
			  }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		   return map.get(Gender.MALE)>map.get(Gender.FEMALE)?Gender.MALE:Gender.FEMALE;
	}
	@Override
	public Person youngestPerson(Connection connection,String query) {
		   return getPersonFromDao(connection, query);
	}
	@Override
	public boolean doSomeoneDoNotFeedPet(Connection connection, String query) {
		try {
			connection.setAutoCommit(false);
			Statement stm = connection.createStatement();
			ResultSet result = stm.executeQuery(query);
			while(result.next()) {
				String name = result.getString("person_name");
				if(name!=null) {
					return true;
				}
			}
			stm.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	@Override
	public List<Person> findPeople_LikeCat_UnlikeDog_Female_18to21(Connection connection, String query){
		return getListPeopleFromDao(connection, query)	;
	}
	
	
	


	

	
	

	
	

}
