package seedu.address.model.util;

import seedu.address.logic.parser.exceptions.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {

    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String MESSAGE_CONSTRAINTS = "Dates should be in form: " + DATE_FORMAT;

    private LocalDate date;

    public Date(LocalDate date) {
        this.date = date;
    }

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

    public static Date parse(String date) {
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
