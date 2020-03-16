package by.bsuir.document.processor.impl.contractor.name;

import by.bsuir.core.exceptions.DataManipulateException;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.model.entity.contractor.Contractor;
import by.bsuir.repository.ContractorRepository;
import lombok.AllArgsConstructor;

/**
 * General implementation for {@link Contractor#getFullName()} processors.
 *
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
public abstract class ContractorNameProcessor implements TagProcessor {
    private ContractorRepository contractorRepository;

    public Object processById(String id) {
        Contractor contractor = contractorRepository.findById(id).orElseThrow(DataManipulateException::new);
        return contractor.getFullName();
    }
}
