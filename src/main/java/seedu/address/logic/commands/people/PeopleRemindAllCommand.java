package seedu.address.logic.commands.people;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_EMAIL_ERROR;

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

    public static final String MESSAGE_REMINDALL_SUCCESS = "Reminded %1$s to return %2$s!\n";
    public static final String MESSAGE_REMINDALL_SUCCESS_EMAIL = "Sharkie has "
            + "sent copies of the reminders to your email!";

    public static final String MESSAGE_HAS_ZERO_LOAN = "No one owes you money :(";

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
                    feedbackToUser += String.format(MESSAGE_REMINDALL_SUCCESS, person.getName(),
                            person.getLoans().getTotal());
                    numberOfPeopleReminded++;
                }
            }
        } catch (MessagingException e) {
            throw new CommandException(String.format(MESSAGE_EMAIL_ERROR, e.getMessage()));
        }
        feedbackToUser += MESSAGE_REMINDALL_SUCCESS_EMAIL;

        if (numberOfPeopleReminded == 0) {
            throw new CommandException(MESSAGE_HAS_ZERO_LOAN);
        }

        return new CommandResult(feedbackToUser);
    }
}
