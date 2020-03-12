package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_LOAN_DISPLAYED_INDEX = "The loan index provided is invalid";
    public static final String MESSAGE_EMPTY_USER_DATA = "Missing user data!\n"
            + "Please proceed to 'Edit > Edit user's data' to include your contact details.\n"
            + "User data has to be saved before using the following command.";
    public static final String MESSAGE_EMAIL_ERROR = "Error occured while sending email:\n%1$s"
            + "\nPlease make sure that you are connected to the internet.\n"
            + "Please check that the receiver's email address is a valid email!";
    public static final String MESSAGE_PERSONS_LISTED_OVERVIEW = "%1$d persons listed!";

}
