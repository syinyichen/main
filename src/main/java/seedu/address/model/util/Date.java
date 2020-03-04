package seedu.address.model.util;

import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Date {

    public static final String DATE_FORMAT = "dd/mm/yyyy";
    public static final String MESSAGE_CONSTRAINTS = "Dates should be in form: " + DATE_FORMAT;

    private LocalDate date;

    public Date(LocalDate date) {
        this.date = date;
    }

    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, DateTimeFormatter.ofPattern(DATE_FORMAT));
            return true;
        } catch (DateTimeParseException exception) {
            return false;
        }
    }

    public static Date parse(String date) {
        return new Date(LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)));
    }

    public LocalDate getDate() {
        return date;
    }
}
