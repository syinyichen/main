package seedu.address.logic.parser.people;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.people.PeopleFindCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PeopleEmailPredicate;
import seedu.address.model.person.PeopleNamePredicate;
import seedu.address.model.person.PeoplePhonePredicate;
import seedu.address.model.person.PeoplePredicate;
import seedu.address.model.person.PeopleTagPredicate;

/**
 * Parses input arguments and creates a new PeopleFindCommand object
 */
public class PeopleFindCommandParser implements Parser<PeopleFindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PeopleFindCommand
     * and returns a PeopleFindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public PeopleFindCommand parse(String args) throws ParseException {

        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_TAG);

        PeoplePredicate predicate = null;

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            predicate = new PeopleNamePredicate(Arrays.asList(nameKeywords));
        } else if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String[] phoneKeywords = argMultimap.getValue(PREFIX_PHONE).get().split("\\s+");
            predicate = new PeoplePhonePredicate(Arrays.asList(phoneKeywords));
        } else if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String[] emailKeywords = argMultimap.getValue(PREFIX_EMAIL).get().split("\\s+");
            predicate = new PeopleEmailPredicate(Arrays.asList(emailKeywords));
        } else if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String[] tagKeywords = argMultimap.getValue(PREFIX_TAG).get().split("\\s+");
            predicate = new PeopleTagPredicate(Arrays.asList(tagKeywords));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeopleFindCommand.MESSAGE_USAGE));
        }

        return new PeopleFindCommand(predicate);
    }

}
