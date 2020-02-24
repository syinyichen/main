package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a Debt.
 * Guarantees: immutable.
 */
public class Debt {

    public final String value;
    private final Amount amount;
    private final LocalDate date;

    /**
     * Constructs a Debt object.
     */
    public Debt(Amount amount, LocalDate date) {
        requireAllNonNull(amount, date);
        this.amount = amount;
        this.date = date;
        this.value = amount + "," + date;
    }

    /**
     * Constructs a Debt object from {@code value} with format "amount,date".
     * Used in Json.
     */
    public Debt(String value) {
        String[] details = value.split(",");
        this.amount = new Amount(Double.valueOf(details[0].substring(1)));
        this.date = LocalDate.parse(details[1]);
        this.value = value;
    }

    public Amount getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * Adds a {@code newDebt} to the current debt and returns a new Debt object.
     */
    public Debt addDebt(Debt newDebt) {
        Amount total = amount.add(newDebt.getAmount());
        return new Debt(total, newDebt.date);
    }

    @Override
    public String toString() {
        if (amount.amount == 0) {
            return "You don't owe any money to this person :)";
        }
        final StringBuilder builder = new StringBuilder();
        builder.append(" Owe this person ")
                .append(getAmount())
                .append(" on ")
                .append(getDate());
        return builder.toString();
    }

    /**
     * Returns true if both Debt has same amount and date.
     */
    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Debt)) {
            return false;
        }

        // state check
        Debt otherDebt = (Debt) other;

        return getAmount().equals(otherDebt.getAmount())
                && getDate().equals(otherDebt.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, date);
    }
}
