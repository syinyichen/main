package seedu.address.model.transaction;

import java.time.LocalDate;
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
    private Budget defaultBudget;

    public BudgetList() {
        budgetList = new ArrayList<Budget>();
        defaultBudget = new Budget(new Amount(0.0), Date.getDefault());
    }

    /**
     * Compares {@code budget} with the budgets that have been previously set in the list, using only their
     * dates. Returns true if a budget with the same month and year is in the list.
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
     * Compares {@code date} with the dates of the Budgets in the list of Budgets. Returns true if the budget exists.
     */
    public boolean hasBudgetOfDate(Date date) {
        for (Budget b : budgetList) {
            if (date.equals(b.getDate())) {
                return true;
            }
        }

        return false;
    }

    public Budget getBudgetOfDate(Date date) {
        for (Budget b : budgetList) {
            if (date.equals(b.getDate())) {
                return b;
            }
        }

        return defaultBudget;
    }

    public void setDefaultBudget(Budget budget) {
        this.defaultBudget = budget;
    }

    public Budget getDefaultBudget() {
        return defaultBudget;
    }

    /**
     * Sets the budget in the list to {@code budget}. If it doesn't exist, add the budget into the list instead, and
     * sort.
     */
    public void setBudget(Budget budget) {
        budgetList.removeIf(b -> b.getDate().equals(budget.getDate()));
        budgetList.add(budget);
    }
}
