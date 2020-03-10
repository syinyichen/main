package seedu.address.testutil;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.Date;

/**
 * A utility class to help with building Budget objects.
 */
public class BudgetBuilder {
    public static final double DEFAULT_AMOUNT = 0;

    private Amount amount;
    private Date date;
    private boolean isDefault;

    public BudgetBuilder() {
        this.amount = new Amount(DEFAULT_AMOUNT);
        this.date = Date.getDefault();
        isDefault = false;
    }

    /**
     * Initializes the BudgetBuilder with the data of {@code budgetToCopy}.
     */
    public BudgetBuilder(Budget budgetToCopy) {
        this.amount = budgetToCopy.getAmount();
        this.date = budgetToCopy.getDate();
        isDefault = budgetToCopy.isDefault();
    }

    /**
     * Sets the {@code amount} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withAmount(String amount) {
        try {
            this.amount = ParserUtil.parseAmount(amount);
            return this;
        } catch (ParseException e) {
            return this;
        }
    }

    /**
     * Sets the {@code date} of the {@code Budget} that we are building.
     */
    public BudgetBuilder withDate(String date) {
        try {
            this.date = ParserUtil.parseDate(date);
            return this;
        } catch (ParseException e) {
            return this;
        }
    }

    /**
     * Sets the type (default or normal) of the {@code Budget} that we are building.
     */
    public BudgetBuilder setAsDefault() {
        this.isDefault = true;
        return this;
    }

    /**
     * Returns a {@code Budget} object.
     */
    public Budget buildBudget() {
        Budget newBudget = new Budget(amount, date);
        if (isDefault) {
            newBudget.setAsDefault();
        }
        return newBudget;
    }
}
