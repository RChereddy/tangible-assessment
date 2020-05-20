package com.abnamro.assessment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.abnamro.assessment.service.PersonService;
import com.abnamro.assessment.model.Person;
import java.util.List;
import javax.inject.Inject;


@RestController
public class PersonController {

      private PersonService personService = new PersonService();
    

    @GetMapping("/persons")
    public List<Person> getPersons() throws Exception{
        return personService.listFilteredPersons();
    }

    @PostMapping("/person")
    public void createPerson(Person person){
        personService.createPerson(person);
    }

}