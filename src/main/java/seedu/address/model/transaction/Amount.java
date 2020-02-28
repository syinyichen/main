package seedu.address.model.transaction;

/**
 * Represents an Amount of money.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Amount implements Comparable<Amount> {

    public final double amount;

    /**
     * Constructs an {@code Amount}.
     *
     * @param amount A valid amount of money.
     */
    public Amount(double amount) {
        this.amount = amount;
    }

    /**
     * Returns true if a given amount (in String form) is valid.
     */
    public static boolean isValidAmount(String test) {
        try {
            Double.parseDouble(test.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    @Override
    public String toString() {
        return String.format("$%.2f", amount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && amount == ((Amount) other).amount); // state check
    }

    @Override
    public int hashCode() {
        return Double.valueOf(amount).hashCode();
    }

    @Override
    public int compareTo(Amount o) {
        return Double.compare(amount, o.amount);
    }

}
