package seedu.address.model.transaction;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Budget for the wallet.
 */
public class Budget {
    private Amount budget;
    private Date date;

    public Budget(Amount budget, Date date) {
        requireAllNonNull(budget, date);
        this.budget = budget;
        this.date = date;
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
}
