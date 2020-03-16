package by.bsuir.document.processor.impl.contractor.addr;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.document.processor.impl.contractor.pan.ContractorPanProcessor;
import by.bsuir.repository.ContractorRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

import static by.bsuir.model.entity.contractor.SpecialContractor.CUSTOMER;

/**
 * {@link TagProcessor} of {@link Tag#CUST_PAN}.
 *
 * @author Vladislav Novitskiy
 */
@Component
public class CustAddrProcessor extends ContractorAddrProcessor {
    public CustAddrProcessor(ContractorRepository contractorRepository) {
        super(contractorRepository);
    }

    @Override
    public Object process(Map<Tag.Param, String> paramValues) {
        return processById(CUSTOMER);
    }
}
