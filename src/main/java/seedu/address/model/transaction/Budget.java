package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Month;
import java.time.Year;

/**
 * Represents a Budget for the wallet.
 */
public class Budget {
    public static final String BUDGET_OK = "Your current expenditure is: %1$s / %2$s";
    public static final String BUDGET_EXCEEDED = "You have exceeded your budget by: %1$s / %2$s";

    private Amount budget;
    private Month month;
    private Year year;
    private boolean isDefault = false;

    public Budget(Amount budget) {
        this.budget = budget;
        this.month = Month.of(1);
        this.year = Year.of(1);
        isDefault = true;
    }

    public Budget(Amount budget, Month month, Year year) {
        requireAllNonNull(budget, month, year);
        this.budget = budget;
        this.month = month;
        this.year = year;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public Amount getAmount() {
        return budget;
    }

    public Month getMonth() {
        return month;
    }

    public Year getYear() {
        return year;
    }

    public static Budget getDefault() {
        return new Budget(new Amount(0), Month.of(1), Year.of(1));
    }

    public boolean dateEquals(Budget other) {
        requireNonNull(other);

        return month.equals(other.getMonth())
                && year.equals(other.getYear());
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof Budget
                && budget.equals(((Budget) other).getAmount())
                && month.equals(((Budget) other).getMonth())
                && year.equals(((Budget) other).getYear());
    }

    @Override
    public String toString() {
        return budget.toString();
    }
}
