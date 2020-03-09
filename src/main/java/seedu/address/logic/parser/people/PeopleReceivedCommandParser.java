package seedu.address.logic.parser.people;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.people.PeopleReceivedCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PeoplePaidCommand object
 */
public class PeopleReceivedCommandParser implements Parser<PeopleReceivedCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PeoplePaidCommand
     * and returns a PeoplePaidCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public PeopleReceivedCommand parse(String args) throws ParseException {

        requireNonNull(args);
        String[] tokenizedIndex = args.trim().split(" ");

        if (tokenizedIndex.length < 1 || tokenizedIndex.length > 2) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeopleReceivedCommand.MESSAGE_USAGE));
        }

        try {
            Index personIndex = ParserUtil.parseIndex(tokenizedIndex[0]);
            if (tokenizedIndex.length == 1) {
                return new PeopleReceivedCommand(personIndex);
            } else {
                Index loanIndex = ParserUtil.parseIndex(tokenizedIndex[1]);
                return new PeopleReceivedCommand(personIndex, loanIndex);
            }
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeopleReceivedCommand.MESSAGE_USAGE), pe);
        }
    }
}

