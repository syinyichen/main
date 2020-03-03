package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Income;
import seedu.address.model.transaction.Transaction;

/**
 * Unmodifiable view of a Wallet
 */
public interface ReadOnlyWallet {

    /**
     * Returns an unmodifiable view of the list of Transactions.
     */
    ObservableList<Transaction> getTransactionList();

    /**
     * Returns an unmodifiable view of the list of Incomes.
     */
    ObservableList<Income> getIncomeList();

    /**
     * Returns an unmodifiable view of the list of Expenses.
     */
    ObservableList<Expense> getExpenseList();

}
