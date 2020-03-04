package seedu.address.model.transaction;

import java.time.LocalDate;
import java.util.Objects;

import seedu.address.model.tag.Tag;

/**
 * Represents a loan, money which the user lends.
 */
public class Loan extends Transaction {
    /**
     * Constructs a Loans object.
     */
    public Loan(Description description, Amount amount, LocalDate date) {
        super(description, amount, date, new Tag("Loan"));
    }

    /**
     * Checks if both Loans are the same.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Loan)) {
            return false;
        }

        Loan otherLoan = (Loan) other;
        return otherLoan.getDescription().equals(getDescription())
                && otherLoan.getAmount().equals(getAmount())
                && otherLoan.getDate().equals(getDate());
    }

    @Override
    public int hashCode() {
        // use "l" to differentiate from other transactions
        return Objects.hash("l", description, amount, date, tag);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Loan description: ")
                .append(getDescription())
                .append(" Amount: ")
                .append(getAmount())
                .append(" Date: ")
                .append(getDate());
        return builder.toString();
    }
}
