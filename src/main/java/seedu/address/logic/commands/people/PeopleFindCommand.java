package seedu.address.logic.commands.people;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliPrefix.PEOPLE_COMMAND_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.PeoplePredicate;

/**
 * Finds and lists all persons in address book that contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class PeopleFindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons that contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: <prefix> <keywords>...\n"
            + "Examples: " + PEOPLE_COMMAND_TYPE + " "
            + COMMAND_WORD + " " + PREFIX_NAME + "alice bob charlie\n"
            + PEOPLE_COMMAND_TYPE + " " + COMMAND_WORD + " " + PREFIX_PHONE + "91234 8834 (Finds people "
            + "with phone number that contains 91234 or 8834)\n"
            + PEOPLE_COMMAND_TYPE + " " + COMMAND_WORD + " " + PREFIX_EMAIL + "gmail yahoo (Finds people "
            + "that use either gmail or yahoo emails)\n"
            + PEOPLE_COMMAND_TYPE + " " + COMMAND_WORD + " " + PREFIX_TAG + "friend colleague (Finds people "
            + "that has either 'friend' or 'colleague' tag)";

    public static final String ONLY_ONE_PARAMETER_ALLOWED = "Only 1 parameter is allowed.\n"
            + "Example: \n" + PEOPLE_COMMAND_TYPE + " " + COMMAND_WORD + " n/Alex Sarah\n"
            + PEOPLE_COMMAND_TYPE + " " + COMMAND_WORD + " t/Friend\n"
            + "This is not allowed: " + PEOPLE_COMMAND_TYPE + " " + COMMAND_WORD + " n/Alex t/Friend";

    private final PeoplePredicate predicate;

    public PeopleFindCommand(PeoplePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PeopleFindCommand // instanceof handles nulls
                && predicate.equals(((PeopleFindCommand) other).predicate)); // state check
    }
}
