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

    public Person create(Person item) {
        item.setId(currentId++);
        persons.put(item.getId(), item);
        return item;
    }

    public Person update(Person item) {
        persons.put(item.getId(), item);
        return item;
    }

    public void delete(Person item) {
        persons.remove(item.getId());
    }
}
