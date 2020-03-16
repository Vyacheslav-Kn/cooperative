package by.bsuir.document.processor.impl.contractor.name;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.repository.ContractorRepository;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.SUBCONTR_ID;

/**
 * {@link TagProcessor} of {@link Tag#SUBCONTR_NAME}.
 *
 * @author Vladislav Novitskiy
 */
public class SubcontrNameProcessor extends ContractorNameProcessor {
    public SubcontrNameProcessor(ContractorRepository contractorRepository) {
        super(contractorRepository);
    }

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        return processById(paramValues.get(SUBCONTR_ID));
    }
}
