package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Budget for the wallet.
 */
public class Budget {
    public static final String BUDGET_OK = "Your current expenditure is: %1$s / %2$s";
    public static final String BUDGET_EXCEEDED = "You have exceeded your budget by: %1$s / %2$s";

    private Amount budget;
    private Date date;
    private boolean isDefault = false;

    public Budget(Amount budget, Date date) {
        requireAllNonNull(budget, date);
        this.budget = budget;
        this.date = date;
    }

    public void setDefault(boolean value) {
        isDefault = value;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public Amount getAmount() {
        return budget;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || other instanceof Budget
                && budget.equals(((Budget) other).getAmount())
                && date.equals(((Budget) other).getDate());
    }

    @Override
    public String toString() {
        return budget.toString();
    }
}
