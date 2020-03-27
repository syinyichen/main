package seedu.address.logic.commands.wallet;

import static seedu.address.logic.parser.CliPrefix.WALLET_COMMAND_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.transaction.WalletPredicate;

/**
 * Finds and lists all transactions in wallet book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class WalletFindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all transactions with description containing "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: prefix/KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + WALLET_COMMAND_TYPE + " "
            + COMMAND_WORD + " n/rice water chicken\n"
            + WALLET_COMMAND_TYPE + " "
            + COMMAND_WORD + " $/12.90 5.80\n"
            + WALLET_COMMAND_TYPE + " "
            + COMMAND_WORD + " d/11/11/2011 22/02/2020\n"
            + WALLET_COMMAND_TYPE + " "
            + COMMAND_WORD + " t/food shopping\n";

    public static final String NO_PARAMETER_INPUTTED = "At least one field to find must be provided.";

    public static final String WRONG_AMT = "Amount to find must be a whole integer. Doubles or decimals are not allowed"
            + "Example: $/ 5 10";

    private final WalletPredicate predicate;

    public WalletFindCommand(WalletPredicate predicate) {
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
