package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class DebtTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Debt(null, null));
    }

    @Test
    public void equals() {
        Debt debt = new Debt("$4.00,2020-02-02");
        Debt debtCopy = new Debt("$4.00,2020-02-02");
        Debt debtDiffAmt = new Debt("$10.00,2020-02-02");
        Debt debtDiffDate = new Debt("$4.00,2020-02-01");

        // same values -> returns true
        assertTrue(debt.equals(debtCopy));

        // same object -> returns true
        assertTrue(debt.equals(debt));

        // null -> returns false
        assertFalse(debt.equals(null));

        // different type -> returns false
        assertFalse(debt.equals(5));

        // different amount -> returns false
        assertFalse(debt.equals(debtDiffAmt));

        // different date -> returns false
        assertFalse(debt.equals(debtDiffDate));

    }
}
