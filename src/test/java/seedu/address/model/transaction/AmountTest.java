package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {

    @Test
    public void isValidAmount_onlyAcceptsValidDoubles() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // invalid amount
        assertFalse(Amount.isValidAmount("")); // empty string
        assertFalse(Amount.isValidAmount("0.0.0")); // multiple decimal points
        assertFalse(Amount.isValidAmount("$9")); // contains non-numeric characters

        // valid amount
        assertTrue(Amount.isValidAmount("0"));
        assertTrue(Amount.isValidAmount("0.00"));
        assertTrue(Amount.isValidAmount("11.12345"));
    }

    @Test
    public void toString_formatsToTwoDecimalPlaces() {
        assertEquals("$0.00", new Amount(0).toString()); // 0 dollars
        assertEquals("$1.12", new Amount(1.12345).toString()); // rounds down
        assertEquals("$9.99", new Amount(9.98765).toString()); // rounds up
    }

    @Test
    public void compareTo_comparesAmountsCorrectly() {
        assertTrue(new Amount(0).compareTo(new Amount(1)) < 0); // 0 is less than 1
        assertTrue(new Amount(0).compareTo(new Amount(0.00)) == 0); // 0 equals 0
    }

}
