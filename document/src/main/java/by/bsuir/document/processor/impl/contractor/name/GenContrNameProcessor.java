package by.bsuir.document.processor.impl.contractor.name;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.repository.ContractorRepository;

import java.util.Map;

import static by.bsuir.model.entity.contractor.SpecialContractor.GENERAL_CONTRACTOR;

/**
 * {@link TagProcessor} of {@link Tag#GEN_CONTR_NAME}.
 *
 * @author Vladislav Novitskiy
 */
public class GenContrNameProcessor extends ContractorNameProcessor {
    public GenContrNameProcessor(ContractorRepository contractorRepository) {
        super(contractorRepository);
    }

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        return processById(GENERAL_CONTRACTOR);
    }
}
