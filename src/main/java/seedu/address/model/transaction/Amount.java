package seedu.address.model.transaction;

/**
 * Represents an Amount of money.
 * Guarantees: immutable; is valid as declared in {@link #isValidAmount(String)}
 */
public class Amount implements Comparable<Amount> {
    public static final String MESSAGE_CONSTRAINTS =
            "Amount of money should be positive and have only up to two decimal places.";
    public static final String VALIDATION_REGEX = "\\d+([.]\\d{1,2})?";

    // Amount is stored in cents to prevent floating point errors.
    public final long amountInCents;

    /**
     * Constructs an {@code Amount}. Private constructor accepts a long.
     */
    private Amount(long amountInCents) {
        this.amountInCents = amountInCents;
    }
    /**
     * Constructs an {@code Amount}. Public constructor accepts a double.
     *
     * @param amount A valid amount of money.
     */

    public Amount(double amountInDollars) {
        this(Math.round(amountInDollars * 100));
    }

    /**
     * Returns true if a given amount (in String form) is valid.
     */
    public static boolean isValidAmount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns an Amount equivalent to zero.
     */
    public static Amount zero() {
        return new Amount(0);
    }

    // @@author cheyannesim
    /**
     * Adds {@code amountToAdd} to the current amount.
     *
     * @return the total amount after adding.
     */
    public Amount add(Amount amountToAdd) {
        long totalAmount = amountToAdd.amountInCents + amountInCents;
        return new Amount(totalAmount);
    }
    // @@author

    /**
     * Returns the difference between this amount and a given amount.
     */
    public Amount difference(Amount o) {
        long difference = Math.abs(amountInCents - o.amountInCents);
        return new Amount(difference);
    }

    /**
     * Returns true if the amount is zero.
     */
    public boolean isZero() {
        return this.amountInCents == 0;
    }

    @Override
    public String toString() {
        return "$" + inDollars();
    }

    public String inDollars() {
        return String.format("%d.%02d", amountInCents / 100, amountInCents % 100);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Amount // instanceof handles nulls
                && amountInCents == ((Amount) other).amountInCents); // state check
    }

    @Override
    public int hashCode() {
        return Long.valueOf(amountInCents).hashCode();
    }

    @Override
    public int compareTo(Amount o) {
        return Long.compare(amountInCents, o.amountInCents);
    }

    public boolean isLessThan(Amount o) {
        return amountInCents < o.amountInCents;
    }
}
