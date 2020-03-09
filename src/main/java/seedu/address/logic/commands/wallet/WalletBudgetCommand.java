package seedu.address.logic.commands.wallet;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliPrefix.WALLET_COMMAND_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.logging.Logger;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.transaction.Budget;

/**
 * Records a budget as set by the user for a selected month.
 * If no date is indicated, the budget will be set for all months.
 */

public class WalletBudgetCommand extends Command {

    public static final String COMMAND_WORD = "budget";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a budget for the user. "
            + "Parameters: "
            + PREFIX_AMOUNT + "<amount> "
            + "[" + PREFIX_DATE + "<date:dd/mm/yyyy>]\n"
            + "Example: " + WALLET_COMMAND_TYPE + " "
            + COMMAND_WORD + " "
            + PREFIX_AMOUNT + "1000 "
            + PREFIX_DATE + "10/10/2020 \n";

    public static final String MESSAGE_SUCCESS_DEFAULT = "Default budget has been set at %1$s.";
    public static final String MESSAGE_SUCCESS = "Budget has been set at %1$s for %2$s %3$s.";

    private final Budget budget;

    public WalletBudgetCommand(Budget budget) {
        requireNonNull(budget);
        this.budget = budget;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (budget.isDefault()) {
            model.setDefaultBudget(budget);
            return new CommandResult(String.format(MESSAGE_SUCCESS_DEFAULT, budget.getAmount()));
        } else {
            model.setBudget(budget);
            return new CommandResult(String.format(MESSAGE_SUCCESS, budget.getAmount(),
                    budget.getDate().getMonthString(),
                    budget.getDate().getYear()));
        }

    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof WalletBudgetCommand
                && budget.equals(((WalletBudgetCommand) other).budget);
    }
}
