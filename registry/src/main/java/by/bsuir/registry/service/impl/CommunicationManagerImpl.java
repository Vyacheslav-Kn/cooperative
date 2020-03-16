package by.bsuir.registry.service.impl;

import by.bsuir.core.exceptions.DataManipulateException;
import by.bsuir.core.service.impl.BaseManagerImpl;
import by.bsuir.registry.model.Communication;
import by.bsuir.registry.model.Person;
import by.bsuir.registry.repository.CommunicationRepository;
import by.bsuir.core.service.BaseManager;
import by.bsuir.registry.service.CommunicationManager;
import org.springframework.stereotype.Service;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Collection;
import java.util.Collections;

@Service
@ParametersAreNonnullByDefault
public class CommunicationManagerImpl extends BaseManagerImpl<CommunicationRepository, Communication> implements CommunicationManager {

    private final BaseManager<Person> personBaseManager;

    public CommunicationManagerImpl(CommunicationRepository mongoRepository, BaseManager<Person> personBaseManager) {
        super(mongoRepository);
        this.personBaseManager = personBaseManager;
    }

    @Override
    public Communication register(Communication communication) throws DataManipulateException {
        if (personBaseManager.find(communication.getPersonId()) != null) {
            return super.register(communication);
        }
        throw new DataManipulateException();
    }

    @Override
    public void delete(Communication communication) throws DataManipulateException {
        mongoRepository.delete(communication);
    }

    @Override
    public Collection<Communication> findByPersonId(String id) {
        if (personBaseManager.find(id) != null) {
            return mongoRepository.findByPersonIdIn(Collections.singleton(id));
        }
        throw new DataManipulateException();
    }

    @Override
    public Collection<Communication> findByPersonId(Collection<String> ids) {
        if (personBaseManager.find(ids) != null) {
            return mongoRepository.findByPersonIdIn(ids);
        }
        throw new DataManipulateException();
    }

    @Override
    public Communication update(Communication communication) {
        if (personBaseManager.find(communication.getPersonId()) != null
                && mongoRepository.findById(communication.getId()).isPresent()) {
            return mongoRepository.save(communication);
        }
        throw new DataManipulateException();
    }

}
