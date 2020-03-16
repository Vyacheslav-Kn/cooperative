package by.bsuir.document.processor.impl.contractor.pan;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.document.processor.impl.contractor.name.ContractorNameProcessor;
import by.bsuir.repository.ContractorRepository;

import java.util.Map;

import static by.bsuir.model.entity.contractor.SpecialContractor.GENERAL_CONTRACTOR;

/**
 * {@link TagProcessor} of {@link Tag#GEN_CONTR_PAN}.
 *
 * @author Vladislav Novitskiy
 */
public class GenContrPanProcessor extends ContractorPanProcessor {
    public GenContrPanProcessor(ContractorRepository contractorRepository) {
        super(contractorRepository);
    }

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        return processById(GENERAL_CONTRACTOR);
    }
}
