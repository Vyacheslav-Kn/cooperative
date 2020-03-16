package by.bsuir.document.service.tag;

import by.bsuir.document.model.template.Tag;

/**
 * Service manager for delay formatting.
 *
 * @author Vladislav Novitskiy
 */
public interface DateFormatService {
    /**
     * Method format given number of days as years/months number or as concrete date to word representation.
     *
     * @param epochDay TimeUnit seconds since 1970 (start date)
     * @param days     Days number between two dates (delay)
     * @return Formatted line for document
     * @see Tag#AN
     */
    String formatDelay(long epochDay, long days);

    /**
     * Method format given number of days as years/months/days number to word representation.
     *
     * @param days Days number
     * @return Formatted line for document
     * @see Tag#ELIMIN_PERIOD
     */
    String formatDays(long days);
}
