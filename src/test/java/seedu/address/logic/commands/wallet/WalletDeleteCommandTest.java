package seedu.address.logic.commands.wallet;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalWallet.getTypicalWallet;
import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.transaction.Expense;

public class WalletDeleteCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalWallet(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {

        Expense expenseToDelete = model.getFilteredExpenseList().get(INDEX_FIRST_PERSON.getZeroBased());
        WalletDeleteCommand walletDeleteCommand = new WalletDeleteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(WalletDeleteCommand.MESSAGE_DELETE_TRANSACTION_SUCCESS, expenseToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), model.getWallet(), new UserPrefs());
        expectedModel.deleteExpense(expenseToDelete);

        assertCommandSuccess(walletDeleteCommand, model, expectedMessage, expectedModel);
    }
}
