package seedu.address.model.transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A list of Budgets to record individual months' budgets.
 *
 * Supports a limited set of list operations.
 */
public class BudgetList {
    private List<Budget> budgetList;

    public BudgetList() {
        budgetList = new ArrayList<Budget>();
    }

    /**
     * Compares the given budget object with the budgets that have been previously set in the list, using only their
     * dates. If a budget with the same month and year is in the list, the method returns true.
     */
    public boolean hasBudget(Budget budget) {
        for (Budget b : budgetList) {
            if (b.getDate().equals(budget.getDate())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the budget in the list to {@code budget}. If it doesn't exist, add the budget into the list instead, and
     * sort.
     */
    public void setBudget(Budget budget) {
        budgetList.removeIf(b -> b.getDate().equals(budget.getDate()));
        budgetList.add(budget);
        sortList();
    }


    /**
     * Sorts the list according to the date of the Budget.
     */
    public void sortList() {
        budgetList.sort(new Comparator<>() {
            @Override
            public int compare(Budget b1, Budget b2) {
                return Integer.compare(
                        b1.getDate().getMonth() + b1.getDate().getYear(),
                        b2.getDate().getMonth() + b2.getDate().getYear());
            }
        });
    }
}
