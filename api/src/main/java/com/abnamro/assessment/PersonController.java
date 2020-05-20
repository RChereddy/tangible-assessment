package com.abnamro.assessment;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.abnamro.assessment.service.PersonService;
import com.abnamro.assessment.model.Person;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;


@RestController
public class PersonController {

    @AutoWired
    private PersonService personService;
    

    @GetMapping("/persons")
    public List<Person> getPersons(){
       return personService.listFilteredPersons();
    }

    @PostMapping("/person")
    public void createPerson(Person person){
        personService.createPerson(person);
    }

}