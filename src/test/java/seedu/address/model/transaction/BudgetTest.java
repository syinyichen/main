package seedu.address.model.transaction;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class BudgetTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        Amount validAmount = new Amount(500);
        Date validDate = Date.getDefault();

        assertThrows(NullPointerException.class, () -> new Budget(null, null));
        assertThrows(NullPointerException.class, () -> new Budget(null, validDate));
        assertThrows(NullPointerException.class, () -> new Budget(validAmount, null));
    }
}
