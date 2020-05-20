package com.abnamro.assessment.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.abnamro.assessment.model.Person;

/**
 * PersonService
 */

public class PersonService {

     private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
            .createEntityManagerFactory("JavaHelps");

    public void createPerson(Person p) {
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Create a new Person object
            Person person = new Person(p.getName(),p.getBirthDate());
           
            // Save the Person object
            manager.persist(person);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the EntityManager
            manager.close();
        }

    }

    public List<Person> listFilteredPersons() throws Exception{
         EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        //  String bannedYears = getBannedYears();

         List<Person> list = (List<Person>)manager.createNativeQuery("Select name from Person where EXTRACT(YEAR FROM birthDate) not in (1982,199) ", Person.class).getResultList();
        return list;
    }

    private String getBannedYears() throws Exception{

        Path path = Paths.get("banned-years");
        Stream<String> lines = Files.lines(path);
        StringBuffer sb  =new StringBuffer();
        lines.forEach(action ->{
            sb.append(action);
            sb.append(",");
        });

        
        return sb.substring(0, sb.length()-1);
    }
    
}