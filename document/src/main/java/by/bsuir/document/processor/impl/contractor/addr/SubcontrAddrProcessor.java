package by.bsuir.document.processor.impl.contractor.addr;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.document.processor.impl.contractor.pan.ContractorPanProcessor;
import by.bsuir.repository.ContractorRepository;

import java.util.Map;

import static by.bsuir.document.model.template.Tag.Param.SUBCONTR_ID;

/**
 * {@link TagProcessor} of {@link Tag#SUBCONTR_PAN}.
 *
 * @author Vladislav Novitskiy
 */
public class SubcontrAddrProcessor extends ContractorAddrProcessor {
    public SubcontrAddrProcessor(ContractorRepository contractorRepository) {
        super(contractorRepository);
    }

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        return processById(paramValues.get(SUBCONTR_ID));
    }
}
