package by.bsuir.reguisites.service;

import by.bsuir.reguisites.model.SupervisorEntity;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public interface SupervisorService extends CrudService<SupervisorEntity> {
    /**
     * Method builds line consist of full surname and first character of name and patronymic
     * of organization supervisor.
     *
     * @param supervisorEntity Entity from which retrieved this attributes
     * @return Result line
     */
    String getSurnameAndInitials(SupervisorEntity supervisorEntity);
}
