package seedu.address.logic.parser.wallet;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.stream.Stream;

import seedu.address.logic.commands.people.PeopleOweCommand;
import seedu.address.logic.commands.wallet.WalletBudgetCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.Date;

/**
 * Parses user input and creates a new WalletBudgetCommand object
 */
public class WalletBudgetCommandParser implements Parser<WalletBudgetCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the WalletBudgetCommand
     * and returns a WalletBudgetCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public WalletBudgetCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_AMOUNT, PREFIX_DATE);

        if (!arePrefixesPresent(argMultimap, PREFIX_AMOUNT)
                || !argMultimap.getPreamble().isEmpty()) {
            System.out.println(args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, WalletBudgetCommand.MESSAGE_USAGE));
        }

        if (!argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            System.out.println(args);
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, PeopleOweCommand.MESSAGE_USAGE));
        }

        Amount amount = ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get());
        Date date;
        Budget budget;

        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            date = ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get());
            budget = new Budget(amount, date);
        } else {
            date = Date.getDefault();
            budget = new Budget(amount, date);
            budget.setAsDefault();
        }

        return new WalletBudgetCommand(budget);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
