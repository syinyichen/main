package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AmountTest {

    @Test
    public void isValidAmount_onlyAcceptsValidAmounts() {
        // null amount
        assertThrows(NullPointerException.class, () -> Amount.isValidAmount(null));

        // invalid amount
        assertFalse(Amount.isValidAmount("")); // empty string
        assertFalse(Amount.isValidAmount("0.000")); // too many decimal places
        assertFalse(Amount.isValidAmount("$9")); // contains non-numeric characters

        // valid amount
        assertTrue(Amount.isValidAmount("0"));
        assertTrue(Amount.isValidAmount("0.00"));
        assertTrue(Amount.isValidAmount("11.12"));
    }

    @Test
    public void toString_formatsToTwoDecimalPlaces() {
        assertEquals("$0.00", Amount.zero().toString()); // 0 dollars
        assertEquals("$1.12", new Amount(1.12345).toString()); // rounds down
        assertEquals("$9.99", new Amount(9.98765).toString()); // rounds up
    }

    @Test
    public void compareTo_comparesAmountsCorrectly() {
        assertTrue(Amount.zero().compareTo(new Amount(1)) < 0); // 0 is less than 1
        assertTrue(Amount.zero().compareTo(new Amount(0.00)) == 0); // 0 equals 0
    }

    @Test
    public void add_addsAmountCorrectly() {
        Amount twoFifty = new Amount(2.5);
        Amount tenDollars = new Amount(10);
        Amount thousandFive = new Amount(1500);
        assertTrue(new Amount(12.5).equals(twoFifty.add(tenDollars)));
        assertTrue(new Amount(1510).equals(tenDollars.add(thousandFive)));
        assertTrue(new Amount(1502.5).equals(thousandFive.add(twoFifty)));
        assertTrue(new Amount(5).equals(twoFifty.add(twoFifty)));
    }

}
