package by.bsuir.reguisites.service.impl;

import by.bsuir.reguisites.model.SupervisorEntity;
import by.bsuir.reguisites.repository.SupervisorRepository;
import by.bsuir.reguisites.service.SupervisorService;
import org.springframework.stereotype.Service;

import javax.annotation.ParametersAreNonnullByDefault;

@Service
@ParametersAreNonnullByDefault
public class SupervisorServiceImpl extends CrudServiceImpl<SupervisorRepository, SupervisorEntity>
        implements SupervisorService {

    public SupervisorServiceImpl(SupervisorRepository repository) {
        super(repository);
    }

    @Override
    public SupervisorEntity getLast() {
        return repository.findTopByOrderByIdDesc();
    }

    @Override
    public String getSurnameAndInitials(SupervisorEntity supervisorEntity) {
        return supervisorEntity.getSurname() + " " + supervisorEntity.getName().charAt(0) + ". "
                + supervisorEntity.getPatronymic().charAt(0) + ".";
    }
}
