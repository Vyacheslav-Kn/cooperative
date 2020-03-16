package by.bsuir.document.config;

import by.bsuir.document.model.template.Tag;
import by.bsuir.document.processor.TagProcessor;
import by.bsuir.document.processor.impl.*;
import by.bsuir.document.processor.impl.communication.MailProcessor;
import by.bsuir.document.processor.impl.communication.PhoneProcessor;
import by.bsuir.document.processor.impl.fullname.FamilyProcessor;
import by.bsuir.document.processor.impl.fullname.FamilyToProcessor;
import by.bsuir.document.processor.impl.id.IdProcessor;
import com.ibm.icu.text.RuleBasedNumberFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.Locale;
import java.util.Map;

import static by.bsuir.document.model.template.Tag.*;

/**
 * @author Vladislav Novitskiy
 */
@Configuration
public class BeansConfig {
    @Bean
    public RuleBasedNumberFormat textNumberFormatter() {
        return new RuleBasedNumberFormat(Locale.forLanguageTag("ru"), RuleBasedNumberFormat.SPELLOUT);
    }

    @Bean
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    @Autowired
    private AtpProcessor atpProcessor;
    @Autowired
    private IdProcessor idProcessor;
    @Autowired
    private FamilyProcessor familyProcessor;
    @Autowired
    private FamilyToProcessor familyToProcessor;
    @Autowired
    private PaspProcessor paspProcessor;
    @Autowired
    private BoxSqProcessor boxSqProcessor;
    @Autowired
    private FioProcessor fioProcessor;
    @Autowired
    private InvProcessor invProcessor;
    @Autowired
    private MailProcessor mailProcessor;
    @Autowired
    private PhoneProcessor phoneProcessor;
    @Autowired
    private PnProcessor pnProcessor;
    @Autowired
    private PdProcessor pdProcessor;
    @Autowired
    private PwProcessor pwProcessor;
    @Autowired
    private OrgNameProcessor orgNameProcessor;
    @Autowired
    private PanProcessor panProcessor;
    @Autowired
    private PresidentProcessor presidentProcessor;
    @Autowired
    private IndexProcessor indexProcessor;
    @Autowired
    private EliminPeriodProcessor eliminPeriodProcessor;

    @Bean
    public Map<Tag, TagProcessor> tagProcessors() {
        Map<Tag, TagProcessor> processors = new EnumMap<>(Tag.class);
        processors.put(ATP, atpProcessor);
        processors.put(PN, pnProcessor);
        processors.put(FAMILY, familyProcessor);
        processors.put(FAMILY_TO, familyToProcessor);
        processors.put(PASP, paspProcessor);
        processors.put(BOX_SQ, boxSqProcessor);
        processors.put(FIO, fioProcessor);
        processors.put(INV, invProcessor);
        processors.put(MAIL, mailProcessor);
        processors.put(PD, pdProcessor);
        processors.put(PHONE, phoneProcessor);
        processors.put(PW, pwProcessor);
        processors.put(AN, idProcessor);
        processors.put(ORG_NAME, orgNameProcessor);
        processors.put(PAN, panProcessor);
        processors.put(PRESIDENT, presidentProcessor);
        processors.put(IND_DOG, idProcessor);
        processors.put(INDEX, indexProcessor);
        processors.put(ELIMIN_PERIOD, eliminPeriodProcessor);
        return processors;
    }
}
