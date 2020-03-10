package seedu.address.testutil;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Budget;
import seedu.address.model.transaction.Date;

public class BudgetBuilder {
    public static final double DEFAULT_AMOUNT = 0;

    private Amount amount;
    private Date date;

    public BudgetBuilder() {
        this.amount = new Amount(DEFAULT_AMOUNT);
        this.date = Date.getDefault();
    }

    public BudgetBuilder withAmount(String amount) {
        try {
            this.amount = ParserUtil.parseAmount(amount);
            return this;
        } catch (ParseException e) {
            return this;
        }
    }

    public BudgetBuilder withDate(String date) {
        try {
            this.date = ParserUtil.parseDate(date);
            return this;
        } catch (ParseException e) {
            return this;
        }
    }

    public Budget buildBudget() {
        return new Budget(amount, date);
    }
}
