package org.kgromov;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.personservice.Person;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@SpringBootApplication
public class SpringBootSoapPersonWebserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSoapPersonWebserviceApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(PersonRepository repository) {
        return args -> {
            var persons = List.of(
                    buildCreatePersonRequest("John", "Doe", 30, "Male", "1990-01-01"),
                    buildCreatePersonRequest("Jane", "Doe", 25, "Female", "1995-01-01")
            );
            persons.forEach(repository::create);
        };
    }

    private static Person buildCreatePersonRequest(String firstName, String lastName, int age, String gender, String birthDate)
            throws DatatypeConfigurationException {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setGender(gender);
        person.setAge(age);
        person.setBirthDate(getBirthDate(birthDate));
        return person;
    }

    private static XMLGregorianCalendar getBirthDate(String birthDate) throws DatatypeConfigurationException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(birthDate, formatter);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
    }

}
