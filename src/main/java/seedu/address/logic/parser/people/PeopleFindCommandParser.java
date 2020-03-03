package seedu.address.logic.parser.people;

import seedu.address.logic.commands.people.PeopleFindCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class PeopleFindCommandParser implements Parser<PeopleFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PeopleFindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeopleFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new PeopleFindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }

}