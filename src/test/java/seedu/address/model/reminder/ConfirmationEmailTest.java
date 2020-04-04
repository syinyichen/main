package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalUser.ALICE;
import static seedu.address.testutil.TypicalUser.BOB;

import org.junit.jupiter.api.Test;

public class ConfirmationEmailTest {

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConfirmationEmail(null, BENSON.getEmail()));
    }

    @Test
    public void constructor_nullEmail_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConfirmationEmail(ALICE.getName(), null));
    }

    @Test
    public void equals() {
        // same values -> returns true
        ConfirmationEmail original = new ConfirmationEmail(ALICE.getName(), ALICE.getEmail());
        ConfirmationEmail confirmationEmailCopy = new ConfirmationEmail(ALICE.getName(), ALICE.getEmail());
        assertTrue(original.equals(confirmationEmailCopy));

        // same object -> returns true
        assertTrue(original.equals(original));

        // null -> returns false
        assertFalse(original.equals(null));

        // different type -> returns false
        assertFalse(original.equals(5));

        // different name -> returns false
        assertFalse(original.equals(new ConfirmationEmail(BOB.getName(), ALICE.getEmail())));

        // different email -> returns false
        assertFalse(original.equals(new ConfirmationEmail(ALICE.getName(), CARL.getEmail())));
    }

    @Test
    public void hashcode() {
        ConfirmationEmail original = new ConfirmationEmail(ALICE.getName(), ALICE.getEmail());
        ConfirmationEmail confirmationEmailCopy = new ConfirmationEmail(ALICE.getName(), ALICE.getEmail());
        ConfirmationEmail differentName = new ConfirmationEmail(BOB.getName(), ALICE.getEmail());
        ConfirmationEmail differentEmail = new ConfirmationEmail(ALICE.getName(), CARL.getEmail());

        // same values -> returns same hashcode
        assertEquals(original.hashCode(), confirmationEmailCopy.hashCode());

        // different name -> returns different hashcode
        assertNotEquals(original.hashCode(), differentName.hashCode());

        // different email -> returns different hashcode
        assertNotEquals(original.hashCode(), differentEmail.hashCode());
    }
}
