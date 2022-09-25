package com.piseth.java.school.service;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.piseth.java.school.model.Gender;
import com.piseth.java.school.model.Person;
import com.piseth.java.school.model.Pet;

public interface PersonService {
	List<Person> getListPeopleFromDao(Connection connection,String query);
	
	Person getPersonFromDao(Connection connection,String query);
	
	Map<Gender, Long> countNumberOfPeopleByGender(Connection connection,String query);
	
	List<Person> findByNumberOfPetMoreThan(Connection connection,String query);
	
	List<Person> findPeopleFeedCat(Connection connection,String query);
	
	Gender findGenderLikeCatMost(Connection connection,String query);

	boolean doSomeoneDoNotFeedPet(Connection connection,String query);

	Person youngestPerson(Connection connection,String query);
	
    List<Person> findPeople_LikeCat_UnlikeDog_Female_18to21(Connection connection , String query);
}
