package seedu.address.model.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Date in Sharkie.
 * Guarantees: immutable; date is valid as declared in {@link #isValidDate(String)}
 */

public class Date {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String MESSAGE_CONSTRAINTS = "Dates should be in form: " + DATE_FORMAT;

    private LocalDate date;

    /**
     * Constructs a valid Date object.
     * @param date A valid date.
     */
    public Date(LocalDate date) {
        this.date = date;
    }

    /**
     * Returns true if a given String is a valid date format.
     */
    public static boolean isValidDate(String test) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            format.setLenient(false);
            format.parse(test);
        } catch (java.text.ParseException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    /**
     * Creates a new Date object on the given date String.
     */

    public static Date of(String date) {
        return new Date(LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)));
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date.toString();
    }

    @Override
    public boolean equals(Object other) {
        return date.toString().equals(((Date) other).toString());
    }
}
