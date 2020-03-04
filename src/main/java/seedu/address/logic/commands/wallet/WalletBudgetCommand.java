package seedu.address.logic.commands.wallet;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;

public class WalletBudgetCommand extends Command {

    public WalletBudgetCommand(Budget budget) {

    }

    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
