package seedu.address.logic.parser.people;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.people.PeopleOweCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Debt;

/**
 * Parses input arguments and creates a new PeopleOweCommand object
 */
public class PeopleOweCommandParser implements Parser<PeopleOweCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the PeopleOweCommand
     * and returns a PeopleOweCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public PeopleOweCommand parse(String args) throws ParseException {

        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_DATE);

        if (!argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            System.out.println(args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeopleOweCommand.MESSAGE_USAGE));
        }

        Index index;
        LocalDate date;
        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = LocalDate.parse(argMultimap.getValue(PREFIX_DATE).get(),
                    DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } else {
            date = LocalDate.now();
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeopleOweCommand.MESSAGE_USAGE), pe);
        }

        Debt debt = new Debt(amount, date);
        return new PeopleOweCommand(index, debt);
    }
}
