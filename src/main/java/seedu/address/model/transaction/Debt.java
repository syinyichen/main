package seedu.address.model.transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.tag.Tag;

/**
 * Represents a Debt, money which the user owes another person..
 * Guarantees: immutable.
 */
public class Debt extends Transaction {

    /**
     * Constructs a Debt object.
     */
    public Debt(Description description, Amount amount, LocalDate date) {
        super(description, amount, date, new Tag("Debt"));
    }

    public Description getDescription() {
        return description;
    }

    public Amount getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Adds a {@code newDebt} to the {@code currentDebts} list of debts and returns a the new list of debts.
     */
    public static List<Debt> addDebt(Debt newDebt, List<Debt> currentDebts) {
        final List<Debt> newDebts = new ArrayList<>();
        for (Debt debt : currentDebts) {
            newDebts.add(debt);
        }
        newDebts.add(newDebt);
        return newDebts;
    }

    public static Amount getTotalDebt(TransactionList<Debt> debts) {
        double totalDebt = 0;
        for (Debt debt : debts) {
            totalDebt += debt.getAmount().amount;
        }
        return new Amount(totalDebt);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Debt description: ")
                .append(getDescription())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate());
        return builder.toString();
    }

}
