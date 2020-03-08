package seedu.address.testutil;

import static seedu.address.logic.parser.CliPrefix.WALLET_COMMAND_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.time.format.DateTimeFormatter;

import seedu.address.logic.commands.wallet.WalletExpenseCommand;
import seedu.address.logic.commands.wallet.WalletIncomeCommand;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Income;
import seedu.address.model.transaction.Transaction;



/**
 * A utility class for Wallet.
 */
public class WalletUtil {

    /**
     * Returns an expense command string for adding the {@code expense}.
     */
    public static String getExpenseCommand(Expense expense) {
        return WALLET_COMMAND_TYPE + " " + WalletExpenseCommand.COMMAND_WORD + " " + getTransactionDetails(expense);
    }

    /**
     * Returns an income command string for adding the {@code income}.
     */
    public static String getIncomeCommand(Income income) {
        return WALLET_COMMAND_TYPE + " " + WalletIncomeCommand.COMMAND_WORD + " " + getTransactionDetails(income);
    }

    /**
     * Returns the part of command string for the given {@code transaction}'s
     * details.
     */
    public static String getTransactionDetails(Transaction transaction) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + transaction.getDescription().description + " ");
        sb.append(PREFIX_AMOUNT + String.valueOf(transaction.getAmount().amount) + " ");
        sb.append(PREFIX_DATE + transaction.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " ");
        sb.append(PREFIX_TAG + transaction.getTag().tagName);
        return sb.toString();
    }
}
