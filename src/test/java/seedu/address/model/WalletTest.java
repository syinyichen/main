package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalWallet.DUCK_RICE;
import static seedu.address.testutil.TypicalWallet.TA_JOB;
import static seedu.address.testutil.TypicalWallet.getTypicalWallet;

import java.util.Collections;

import org.junit.jupiter.api.Test;


public class WalletTest {

    private final Wallet wallet = new Wallet();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), wallet.getTransactionList());
        assertEquals(Collections.emptyList(), wallet.getIncomeList());
        assertEquals(Collections.emptyList(), wallet.getExpenseList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyWallet_replacesData() {
        Wallet newData = getTypicalWallet();
        wallet.resetData(newData);
        assertEquals(newData, wallet);
    }

    @Test
    public void hasExpense_nullExpense_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.hasExpense(null));
    }

    @Test
    public void hasExpense_expenseNotInWallet_returnsFalse() {
        assertFalse(wallet.hasExpense(DUCK_RICE));
    }

    @Test
    public void hasExpense_expenseInWallet_returnsTrue() {
        wallet.addExpense(DUCK_RICE);
        assertTrue(wallet.hasExpense(DUCK_RICE));
    }

    @Test
    public void hasIncome_nullIncome_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> wallet.hasIncome(null));
    }

    @Test
    public void hasIncome_incomeNotInWallet_returnsFalse() {
        assertFalse(wallet.hasIncome(TA_JOB));
    }

    @Test
    public void hasIncome_incomeInWallet_returnsTrue() {
        wallet.addIncome(TA_JOB);
        assertTrue(wallet.hasIncome(TA_JOB));
    }

    @Test
    public void getIncomeList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wallet.getIncomeList().remove(0));
    }

    @Test
    public void getExpenseList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wallet.getExpenseList().remove(0));
    }

    @Test
    public void getTransactionList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> wallet.getTransactionList().remove(0));
    }

}
