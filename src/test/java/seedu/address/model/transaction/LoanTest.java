package seedu.address.model.transaction;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import seedu.address.testutil.DebtBuilder;
import seedu.address.testutil.LoanBuilder;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLoans.BREAKFAST;

public class LoanTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Loan(null, null, null));
    }

    @Test
    public void equals() {
        Loan breakfastCopy = new LoanBuilder()
                .withDescription("Breakfast")
                .withAmount("5")
                .withDate("2018-08-08").build();
        Debt breakfastDebt = new DebtBuilder()
                .withDescription("Breakfast")
                .withAmount("5")
                .withDate("2018-08-08").build();
        Loan loanDiffDesc = new LoanBuilder()
                .withDescription("Lunch")
                .withAmount("5")
                .withDate("2018-08-08").build();
        Loan loanDiffAmt = new LoanBuilder()
                .withDescription("Breakfast")
                .withAmount("10")
                .withDate("2018-08-08").build();
        Loan loanDiffDate = new LoanBuilder()
                .withDescription("Breakfast")
                .withAmount("5")
                .withDate("2020-01-12").build();

        // same values -> returns true
        assertTrue(BREAKFAST.equals(breakfastCopy));

        // same object -> returns true
        assertTrue(BREAKFAST.equals(BREAKFAST));

        // null -> returns false
        assertFalse(BREAKFAST.equals(null));

        // different type -> returns false
        assertFalse(BREAKFAST.equals(5));

        // different type of transaction -> returns false
        assertFalse(BREAKFAST.equals(breakfastDebt));

        // different description -> returns false
        assertFalse(BREAKFAST.equals(loanDiffDesc));

        // different amount -> returns false
        assertFalse(BREAKFAST.equals(loanDiffAmt));

        // different date -> returns false
        assertFalse(BREAKFAST.equals(loanDiffDate));
    }

}
