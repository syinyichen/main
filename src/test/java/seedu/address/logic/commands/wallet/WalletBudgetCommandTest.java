package seedu.address.logic.commands.wallet;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.people.PeopleAddCommand;

public class WalletBudgetCommandTest {

    @Test
    public void constructor_nullBudget_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new WalletBudgetCommand(null));
    }
}
