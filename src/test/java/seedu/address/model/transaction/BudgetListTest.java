package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalWallet.BUDGET_APRIL_2020;
import static seedu.address.testutil.TypicalWallet.BUDGET_JAN_2010;
import static seedu.address.testutil.TypicalWallet.VALID_BUDGET_MONTH_JAN_2010;
import static seedu.address.testutil.TypicalWallet.VALID_BUDGET_YEAR_JAN_2010;

import java.time.Month;
import java.time.Year;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BudgetBuilder;

public class BudgetListTest {

    private BudgetList<Budget> budgetList = new BudgetList<>();

    @Test
    public void getBudget_nullDate_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.get(null, null));
    }

    @Test
    public void getBudget_doesNotHaveBudget_returnsDefaultBudget() {
        BudgetList<Budget> noDefaultBudgetList = new BudgetList<>();
        Budget defaultBudget = noDefaultBudgetList.getDefaultBudget();
        assertEquals(defaultBudget, noDefaultBudgetList.get(BUDGET_JAN_2010.getMonth(), BUDGET_JAN_2010.getYear()));
    }

    @Test
    public void getBudget_hasBudget_returnsBudget() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        assertEquals(BUDGET_JAN_2010, tempBudgetList.get(BUDGET_JAN_2010.getMonth(), BUDGET_JAN_2010.getYear()));
    }

    @Test
    public void getBudget_hasMultipleBudgets_returnsCorrectBudget() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.add(BUDGET_APRIL_2020);
        assertEquals(BUDGET_JAN_2010, tempBudgetList.get(BUDGET_JAN_2010.getMonth(), BUDGET_JAN_2010.getYear()));
    }

    @Test
    public void getBudget_hasMultipleBudgets_returnsDefaultBudget() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.add(BUDGET_APRIL_2020);

        Budget nonExistentInListBudget =
                new BudgetBuilder().withAmount("3000").withMonth("09").withYear("2069").buildBudget();
        Budget defaultBudget = tempBudgetList.getDefaultBudget();
        assertEquals(defaultBudget, tempBudgetList.get(nonExistentInListBudget.getMonth(),
                nonExistentInListBudget.getYear()));
    }

    @Test
    public void setDefaultBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.setDefaultBudget(null));
    }

    @Test
    public void hasBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.contains(null));
    }

    @Test
    public void hasBudget_existingBudget_returnsTrue() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.add(BUDGET_APRIL_2020);

        assertTrue(tempBudgetList.contains(BUDGET_JAN_2010));
        assertTrue(tempBudgetList.contains(BUDGET_APRIL_2020));
    }

    @Test
    public void hasBudget_nonExistentBudget_returnsFalse() {
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        Budget nonExistentBudget =
                new BudgetBuilder().withAmount("3000").withMonth("09").withYear("2069").buildBudget();

        tempBudgetList.add(BUDGET_JAN_2010);
        tempBudgetList.add(BUDGET_APRIL_2020);

        assertFalse(tempBudgetList.contains(nonExistentBudget));
    }

    @Test
    public void setBudget_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> budgetList.setBudget(null, null));
        assertThrows(NullPointerException.class, () -> budgetList.setBudget(BUDGET_APRIL_2020, null));
        assertThrows(NullPointerException.class, () -> budgetList.setBudget(null, BUDGET_APRIL_2020));
    }

    @Test
    public void addBudget_normalBudget_budgetAddedToList() {
        List<Budget> expectedBudgetList = List.of(BUDGET_JAN_2010);
        BudgetList<Budget> tempBudgetList = new BudgetList<>();
        tempBudgetList.add(BUDGET_JAN_2010);

        assertEquals(expectedBudgetList, tempBudgetList.internalList);
    }
}
