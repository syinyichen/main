package seedu.address.logic.parser.wallet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.wallet.WalletEditCommand;
import seedu.address.logic.commands.wallet.WalletFindCommand;
import seedu.address.logic.commands.wallet.WalletFindCommand.FindTransactionDescriptor;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Date;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.DescriptionContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class WalletFindCommandParser implements Parser<WalletFindCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public WalletFindCommand parse(String args) throws ParseException {
        /*
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, WalletFindCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new WalletFindCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
         */

        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_AMOUNT, PREFIX_DATE, PREFIX_TAG);

        FindTransactionDescriptor findTransactionDescriptor = new FindTransactionDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            findTransactionDescriptor.setDescription(
                    ParserUtil.parseDescription(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_AMOUNT).isPresent()) {
            findTransactionDescriptor.setAmount(ParserUtil.parseAmount(argMultimap.getValue(PREFIX_AMOUNT).get()));
        }
        if (argMultimap.getValue(PREFIX_DATE).isPresent()) {
            findTransactionDescriptor.setDate(ParserUtil.parseDate(argMultimap.getValue(PREFIX_DATE).get()));
        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            findTransactionDescriptor.setTag(ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get()));
        }

        if (!findTransactionDescriptor.isAnyFieldEdited()) {
            throw new ParseException(WalletFindCommand.NO_PARAMETER_INPUTTED);
        }

        return new WalletFindCommand(findTransactionDescriptor);


    }
}
