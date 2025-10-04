package org.kgromov;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import com.example.personservice.GetPersonResponse;
import com.example.personservice.GetPersonRequest;
import com.example.personservice.CreatePersonRequest;
import com.example.personservice.CreatePersonResponse;
import com.example.personservice.UpdatePersonRequest;
import com.example.personservice.UpdatePersonResponse;
import com.example.personservice.DeletePersonRequest;
import com.example.personservice.DeletePersonResponse;
import com.example.personservice.Person;

@Endpoint
public class PersonEndpoint {
    private static final String NAMESPACE_URI = "http://example.com/personservice";

    private final PersonRepository repository;

    public PersonEndpoint(PersonRepository repository) {
        this.repository = repository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
    @ResponsePayload
    public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {
        Person person = repository.findById(request.getId());
        var response = new GetPersonResponse();
        response.setPerson(person);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPersonRequest")
    @ResponsePayload
    public CreatePersonResponse createPerson(@RequestPayload CreatePersonRequest request) {
        Person person = repository.create(request.getPerson());
        CreatePersonResponse response = new CreatePersonResponse();
        response.setPerson(person);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
    @ResponsePayload
    public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) {
        Person existingPerson = repository.findById(request.getPerson().getId());
        if (existingPerson == null) {
            throw new IllegalArgumentException("Person not found");
        }
        Person person = repository.update(request.getPerson());
        var response = new UpdatePersonResponse();
        response.setPerson(person);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
    @ResponsePayload
    public DeletePersonResponse deletePerson(@RequestPayload DeletePersonRequest request) {
        repository.delete(request.getId());
        var response = new DeletePersonResponse();
        response.setSuccess(true);
        return response;
    }
}
