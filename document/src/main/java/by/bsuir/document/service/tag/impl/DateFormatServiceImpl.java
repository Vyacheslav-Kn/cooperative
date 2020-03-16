package by.bsuir.document.service.tag.impl;

import by.bsuir.document.service.tag.DateFormatService;
import com.ibm.icu.text.RuleBasedNumberFormat;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author Vladislav Novitskiy
 */
@AllArgsConstructor
@Service
public class DateFormatServiceImpl implements DateFormatService {
    private RuleBasedNumberFormat textNumberFormatter;
    private DateTimeFormatter dateTimeFormatter;

    @Override
    public String formatDelay(long epochDay, long daysDelay) {
        LocalDate startDay = LocalDate.ofEpochDay(SECONDS.toDays(epochDay));
        LocalDate endDay = startDay.plus(daysDelay, DAYS);

        Period period = Period.between(startDay, endDay);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        if (years != 0 && months == 0 && days == 0) {
            return "на " + years + " (" + textNumberFormatter.format(years) + ") " + getYearWord(years);
        } else if (years == 0 && (months == 1 || months == 3 || months == 6) && days == 0) {
            return "на " + months + " (" + textNumberFormatter.format(months) + ") " + getMonthWord(months);
        } else {
            return "до " + endDay.format(dateTimeFormatter);
        }
    }

    @Override
    public String formatDays(long days) {
        Period period = Period.ofDays((int) days);
        int years = period.getYears();
        int months = period.getMonths();
        if (years != 0 && months == 0 && days == 0) {
            return years + " (" + textNumberFormatter.format(years) + ") " + getYearWord(years);
        } else if (months != 0 && days == 0) {
            long allMonths = years * 12 + months;
            return allMonths + " (" + textNumberFormatter.format(allMonths) + ") " + getMonthWord(allMonths);
        } else {
            return days + " (" + textNumberFormatter.format(days) + ") " + getDayWord(days);
        }
    }

    private String getYearWord(long number) {
        if (number < 1) {
            throw new IllegalArgumentException();
        }

        if (number == 1) {
            return "год";
        } else if (number < 5) {
            return "года";
        } else {
            return "лет";
        }
    }

    private String getMonthWord(long number) {
        if (number < 1) {
            throw new IllegalArgumentException();
        }

        if (number == 1) {
            return "месяц";
        } else if (number < 5) {
            return "месяца";
        } else {
            return "месяцев";
        }
    }

    private String getDayWord(long number) {
        if (number < 1) {
            throw new IllegalArgumentException();
        }

        if (number == 1) {
            return "день";
        } else if (number < 5) {
            return "дня";
        } else {
            return "дней";
        }
    }
}
