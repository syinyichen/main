package seedu.address.logic.commands.people;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import javax.mail.MessagingException;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.User;
import seedu.address.model.reminder.Reminder;

/**
 * Reminds everyone in the address book, who owe the user money.
 */
public class PeopleRemindAllCommand extends Command {

    public static final String COMMAND_WORD = "remindall";

    private static final Logger logger = LogsCenter.getLogger(PeopleRemindCommand.class);

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.isUserDataNull()) {
            throw new CommandException(Messages.MESSAGE_EMPTY_USER_DATA);
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        User user = model.getUserData().getUser();
        int numberOfPeopleReminded = 0;
        String feedbackToUser = "";

        logger.info("Sending reminders...");

        try {
            for (Person person : lastShownList) {
                if (!person.getLoans().getTotal().isZero()) {
                    Reminder reminder = new Reminder(user, person);
                    reminder.sendReminder();
                    feedbackToUser += "Reminded " + person.getName().toString() + " to return "
                            + person.getLoans().getTotal().toString() + "!\n";
                    numberOfPeopleReminded++;
                }
            }
        } catch (MessagingException e) {
            throw new CommandException("Error occured while sending email:\n" + e.getMessage()
                    + "\nPlease make sure that you are connected to the internet.\n"
                    + "Please check that the receiver's email address is a valid email!");
        }
        feedbackToUser += "Sharkie has sent copies of the reminders to your email!";

        if (numberOfPeopleReminded == 0) {
            throw new CommandException("No one owes you money :(");
        }

        return new CommandResult(feedbackToUser);
    }
}
