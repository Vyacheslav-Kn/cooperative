package by.bsuir.document.processor.impl;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.reguisites.model.SupervisorEntity;
import by.bsuir.reguisites.service.SupervisorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * {@link TagProcessor} of {@link Tag#PRESIDENT}.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Component
public class PresidentProcessor implements TagProcessor {
    private SupervisorService supervisorService;

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        SupervisorEntity supervisor = supervisorService.getLast();
        return supervisorService.getSurnameAndInitials(supervisor);
    }
}
