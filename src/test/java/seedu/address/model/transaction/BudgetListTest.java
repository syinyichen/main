package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalWallet.BUDGET_APRIL_2020;
import static seedu.address.testutil.TypicalWallet.BUDGET_JAN_2010;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BudgetBuilder;

public class BudgetListTest {

    private BudgetList budgetList = new BudgetList();

    @Test
    public void getBudget_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.getBudget(null));
    }

    @Test
    public void getBudget_doesNotHaveBudget_returnsDefaultBudget() {
        BudgetList noDefaultBudgetList = new BudgetList();
        Budget defaultBudget = noDefaultBudgetList.getDefaultBudget();
        assertEquals(defaultBudget, noDefaultBudgetList.getBudget(BUDGET_JAN_2010.getDate()));
    }

    @Test
    public void getBudget_hasBudget_returnsBudget() {
        BudgetList tempBudgetList = new BudgetList();
        tempBudgetList.setBudget(BUDGET_JAN_2010);
        assertEquals(BUDGET_JAN_2010, tempBudgetList.getBudget(BUDGET_JAN_2010.getDate()));
    }

    @Test
    public void getBudget_hasMultipleBudgets_returnsBudget() {
        BudgetList tempBudgetList = new BudgetList();
        tempBudgetList.setBudget(BUDGET_JAN_2010);
        tempBudgetList.setBudget(BUDGET_APRIL_2020);
        assertEquals(BUDGET_JAN_2010, tempBudgetList.getBudget(BUDGET_JAN_2010.getDate()));
    }

    @Test
    public void getBudget_hasMultipleBudgets_returnsDefaultBudget() {
        BudgetList tempBudgetList = new BudgetList();
        tempBudgetList.setBudget(BUDGET_JAN_2010);
        tempBudgetList.setBudget(BUDGET_APRIL_2020);

        Budget nonExistentInListBudget = new BudgetBuilder().withAmount("3000").withDate("01/03/2069").buildBudget();
        Budget defaultBudget = tempBudgetList.getDefaultBudget();
        assertEquals(defaultBudget, tempBudgetList.getBudget(nonExistentInListBudget.getDate()));
    }

    @Test
    public void setDefaultBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.setDefaultBudget(null));
    }

    @Test
    public void hasBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.hasBudget(null));
    }

    @Test
    public void hasBudget_existingBudget_returnsTrue() {
        BudgetList tempBudgetList = new BudgetList();
        tempBudgetList.setBudget(BUDGET_JAN_2010);
        tempBudgetList.setBudget(BUDGET_APRIL_2020);

        assertTrue(tempBudgetList.hasBudget(BUDGET_JAN_2010));
        assertTrue(tempBudgetList.hasBudget(BUDGET_APRIL_2020));
    }

    @Test
    public void hasBudget_nonExistentBudget_returnsFalse() {
        BudgetList tempBudgetList = new BudgetList();
        Budget nonExistentBudget = new BudgetBuilder().withAmount("3000").withDate("01/01/2069").buildBudget();

        tempBudgetList.setBudget(BUDGET_JAN_2010);
        tempBudgetList.setBudget(BUDGET_APRIL_2020);

        assertFalse(tempBudgetList.hasBudget(nonExistentBudget));
    }

    @Test
    public void hasBudgetOfDate_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.hasBudgetOfDate(null));
    }

    @Test
    public void setBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.setBudget(null));
    }

    @Test
    public void setBudget_normalBudget_budgetAddedToList() {
        List<Budget> expectedBudgetList = List.of(BUDGET_JAN_2010);
        BudgetList tempBudgetList = new BudgetList();
        tempBudgetList.setBudget(BUDGET_JAN_2010);

        assertEquals(expectedBudgetList, tempBudgetList.budgetList);
    }

    @Test
    public void setBudget_replacedOldBudget_newBudgetAddedToList() {
        Budget oldBudget = new BudgetBuilder().withAmount("300").withDate("01/01/2020").buildBudget();
        Budget newBudget = new BudgetBuilder().withAmount("500").withDate("01/01/2020").buildBudget();

        BudgetList tempBudgetList = new BudgetList();
        tempBudgetList.setBudget(oldBudget);
        tempBudgetList.setBudget(newBudget);

        assertFalse(tempBudgetList.getBudget(newBudget.getDate()).equals(oldBudget));
    }
}
