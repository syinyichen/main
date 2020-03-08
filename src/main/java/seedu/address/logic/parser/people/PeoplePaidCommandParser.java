package seedu.address.logic.parser.people;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.people.PeoplePaidCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new PeoplePaidCommand object
 */
public class PeoplePaidCommandParser implements Parser<PeoplePaidCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PeoplePaidCommand
     * and returns a PeoplePaidCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public PeoplePaidCommand parse(String args) throws ParseException {

        requireNonNull(args);
        String[] tokenizedIndex = args.trim().split(" ");

        if (tokenizedIndex.length < 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeoplePaidCommand.MESSAGE_USAGE));
        }

        try {
            if (tokenizedIndex.length == 1) {
                Index index = ParserUtil.parseIndex(tokenizedIndex[0]);
                return new PeoplePaidCommand(index);
            } else if (tokenizedIndex.length == 2) {
                Index personIndex = ParserUtil.parseIndex(tokenizedIndex[0]);
                Index loanIndex = ParserUtil.parseIndex(tokenizedIndex[1]);
                return new PeoplePaidCommand(personIndex, loanIndex);
            } else {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        PeoplePaidCommand.MESSAGE_USAGE));
            }
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeoplePaidCommand.MESSAGE_USAGE), pe);
        }
    }
}

