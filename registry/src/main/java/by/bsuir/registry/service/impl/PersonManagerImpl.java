package by.bsuir.registry.service.impl;

import by.bsuir.core.exceptions.DataManipulateException;
import by.bsuir.core.service.impl.BaseManagerImpl;
import by.bsuir.registry.model.Person;
import by.bsuir.registry.repository.PersonRepository;
import by.bsuir.registry.service.PersonManager;
import org.springframework.stereotype.Service;

import javax.annotation.ParametersAreNonnullByDefault;

@Service
@ParametersAreNonnullByDefault
public class PersonManagerImpl extends BaseManagerImpl<PersonRepository, Person> implements PersonManager {

    public PersonManagerImpl(PersonRepository mongoRepository) {
        super(mongoRepository);
    }

    @Override
    public Person update(Person person) {
        if (mongoRepository.findById(person.getId()).isPresent()) {
            return mongoRepository.save(person);
        }
        throw new DataManipulateException();
    }

    @Override
    public String getFullName(Person person) {
        return person.getSurname() + " " + person.getName() + " " + person.getLastName();
    }

}
