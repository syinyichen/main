package seedu.address.logic.commands.wallet;

import static seedu.address.logic.parser.CliPrefix.WALLET_COMMAND_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.transaction.DescriptionContainsKeywordsPredicate;

/**
 * Finds and lists all transactions in wallet book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class WalletFindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all transactions whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + WALLET_COMMAND_TYPE + " "
            + COMMAND_WORD + " rice water chicken";

    private final DescriptionContainsKeywordsPredicate predicate;

    public WalletFindCommand(DescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTransactionList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW,
                        model.getFilteredTransactionList().size()));
    }

    private void requireNonNull(Model model) {
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WalletFindCommand // instanceof handles nulls
                && predicate.equals(((WalletFindCommand) other).predicate)); // state check
    }
}
