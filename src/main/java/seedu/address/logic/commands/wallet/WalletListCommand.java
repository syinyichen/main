package seedu.address.logic.commands.wallet;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.transaction.Transaction;

/**
 * Lists all transactions in the address book to the user.
 */
public class WalletListCommand extends Command {
    public static final String COMMAND_WORD = "list";

    public static String MESSAGE_SUCCESS = "";


    @Override
    public CommandResult execute(Model model) {
        MESSAGE_SUCCESS = "Listed all transactions:";

        requireNonNull(model);
        model.updateFilteredTransactionList(Model.PREDICATE_SHOW_ALL_TRANSACTIONS);

        int counter = 1;
        for (Transaction transaction : model.getFilteredTransactionList()) {
            String currTransaction = "" + transaction.getDescription()  + transaction.getTag() + " on "
                    + transaction.getDate() + " --- " + transaction.getAmount();
            MESSAGE_SUCCESS = MESSAGE_SUCCESS + "\n" + counter + ": " + currTransaction;
            counter++;
        }

        return new CommandResult(MESSAGE_SUCCESS);
    }
}
