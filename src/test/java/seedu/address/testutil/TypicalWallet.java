package seedu.address.testutil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.Wallet;
import seedu.address.model.tag.Tag;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Description;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Income;

/**
 * A utility class containing a list of {@code Transaction} objects to be used in tests.
 */
public class TypicalWallet {

    public static final Income ALLOWANCE = new Income(new Description("Allowance from Parents"), new Amount(300),
            LocalDate.of(2020, 1, 1), new Tag("Allowance"));
    public static final Income TA_JOB = new Income(new Description("CS2103 TA"), new Amount(3000),
            LocalDate.of(2020, 1, 1), new Tag("Job"));
    public static final Expense DUCK_RICE = new Expense(new Description("Duck Rice"), new Amount(3.50),
            LocalDate.of(2020, 1, 2), new Tag("Food"));
    public static final Expense MRT_CONCESSION = new Expense(new Description("Monthly MRT Concession pass"),
            new Amount(45), LocalDate.of(2020, 1, 3), new Tag("Transport"));

    private TypicalWallet() {} // prevents instantiation

    /**
     * Returns a {@code Wallet} with all the typical transactions.
     */
    public static Wallet getTypicalWallet() {
        Wallet wallet = new Wallet();
        for (Income income : getTypicalIncomes()) {
            wallet.addIncome(income);
        }
        for (Expense expense : getTypicalExpenses()) {
            wallet.addExpense(expense);
        }
        return wallet;
    }

    public static List<Income> getTypicalIncomes() {
        return new ArrayList<>(Arrays.asList(TA_JOB));
    }

    public static List<Expense> getTypicalExpenses() {
        return new ArrayList<>(Arrays.asList(DUCK_RICE, MRT_CONCESSION));
    }
}
