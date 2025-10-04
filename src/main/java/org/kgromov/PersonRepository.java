package org.kgromov;

import com.example.personservice.Person;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PersonRepository {
    private static final Map<Integer, Person> persons = new HashMap<>();
    private static int currentId = 1;


    public List<Person> findAll() {
        return persons.values().stream().toList();
    }

    public Person findById(int id) {
        return persons.get(id);
    }

    public Person create(Person person) {
        person.setId(currentId++);
        persons.put(person.getId(), person);
        return person;
    }

    public Person update(Person person) {
        persons.put(person.getId(), person);
        return person;
    }

    public void delete(int personId) {
        persons.remove(personId);
    }
}
