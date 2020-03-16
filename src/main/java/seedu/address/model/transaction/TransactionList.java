package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Month;
import java.time.Year;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import seedu.address.model.transaction.exceptions.TransactionNotFoundException;

/**
 * A list of transactions that does not allow nulls.
 * Generics are used to allow creating list of expenses and list of incomes.
 *
 * Supports a minimal set of list operations.
 */
public class TransactionList<T extends Transaction> implements Iterable<T> {

    private final ObservableList<T> internalList = FXCollections.observableArrayList();
    private final ObservableList<T> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns the total amount in a the transaction list.
     */
    public Amount getTotal() {
        double totalAmount = 0;
        for (T transaction : this) {
            totalAmount += transaction.getAmount().amount;
        }
        return new Amount(totalAmount);
    }

    /**
     * Returns true if the list contains an equivalent transaction as the given argument.
     */
    public boolean contains(T toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds a Transaction to the list.
     */
    public void add(T toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
        sortTransactionsByDate();
    }

    /**
     * Replaces the transaction {@code target} in the list with {@code editedTransaction}.
     * {@code target} must exist in the list.
     */
    public void setTransaction(T target, T editedTransaction) {
        requireAllNonNull(target, editedTransaction);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TransactionNotFoundException();
        }

        internalList.set(index, editedTransaction);
        sortTransactionsByDate();
    }

    /**
     * Removes the equivalent transaction from the list.
     * The transaction must exist in the list.
     */
    public void remove(T toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TransactionNotFoundException();
        }
        sortTransactionsByDate();
    }


    public void setTransactions(TransactionList<T> replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
        sortTransactionsByDate();
    }

    /**
     * Replaces the contents of this list with {@code transactions}.
     */
    public void setTransactions(List<T> transactions) {
        requireAllNonNull(transactions);
        internalList.setAll(transactions);
        sortTransactionsByDate();
    }

    /**
     * Retrieves a list of expenses filtered by the month they were added, using {@code date}.
     */
    public TransactionList<T> getTransactionsInMonth(Month month, Year year) {
        TransactionList<T> filteredTransactions = new TransactionList<T>();

        for (T transaction : this) {
            if (transaction.getDate().getMonth().equals(month) && transaction.getDate().getYear().equals(year)) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    public void sortTransactionsByDate() {
        internalList.sort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return -Integer.compare(o1.getDate().getDate().getDayOfYear(), o2.getDate().getDate().getDayOfYear())
                        + Integer.compare(o1.getDate().getMonth().getValue(), o2.getDate().getMonth().getValue())
                        + Integer.compare(o1.getDate().getYear().getValue(), o2.getDate().getYear().getValue());
            }
        });
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<T> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<T> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TransactionList<?> // instanceof handles nulls
                        && internalList.equals(((TransactionList<?>) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

}
