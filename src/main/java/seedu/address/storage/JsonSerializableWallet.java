package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyWallet;
import seedu.address.model.Wallet;
import seedu.address.model.transaction.Expense;
import seedu.address.model.transaction.Income;

/**
 * An Immutable Wallet that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableWallet {

    private final List<JsonAdaptedExpense> expenses = new ArrayList<>();
    private final List<JsonAdaptedIncome> incomes = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableWallet} with the given expenses and incomes.
     */
    @JsonCreator
    public JsonSerializableWallet(@JsonProperty("expenses") List<JsonAdaptedExpense> expenses,
            @JsonProperty("incomes") List<JsonAdaptedIncome> incomes) {
        this.expenses.addAll(expenses);
        this.incomes.addAll(incomes);
    }

    /**
     * Converts a given {@code ReadOnlyWallet} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     *               {@code JsonSerializableWallet}.
     */
    public JsonSerializableWallet(ReadOnlyWallet source) {
        expenses.addAll(source.getExpenseList().stream().map(JsonAdaptedExpense::new).collect(Collectors.toList()));
        incomes.addAll(source.getIncomeList().stream().map(JsonAdaptedIncome::new).collect(Collectors.toList()));
    }

    /**
     * Converts this wallet into the model's {@code Wallet} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Wallet toModelType() throws IllegalValueException {
        Wallet wallet = new Wallet();
        for (JsonAdaptedExpense jsonAdaptedExpense : expenses) {
            Expense expense = jsonAdaptedExpense.toModelType();
            wallet.addExpense(expense);
        }
        for (JsonAdaptedIncome jsonAdaptedIncome : incomes) {
            Income income = jsonAdaptedIncome.toModelType();
            wallet.addIncome(income);
        }
        return wallet;
    }

}
