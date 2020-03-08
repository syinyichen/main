package seedu.address.logic.commands.people;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalUser.getTypicalUserData;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.AddressBookBuilder;
import seedu.address.testutil.PersonBuilder;

public class PeopleRemindAllCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_emptyUserData_success() {

        PeopleRemindAllCommand peopleRemindallCommand = new PeopleRemindAllCommand();

        assertCommandFailure(peopleRemindallCommand, model, Messages.MESSAGE_EMPTY_USER_DATA);
    }

    @Test
    public void execute_unfilteredList_success() {
        model.setUserData(getTypicalUserData());
        List<Person> lastShownList = model.getFilteredPersonList();

        String expectedMessage = "";
        for (Person p: lastShownList) {
            if (!p.getLoans().getTotal().isZero()) {
                expectedMessage += "Reminded " + p.getName().toString() + " to return "
                        + p.getLoans().getTotal().toString() + "!\n";
            }
        }
        expectedMessage += "Sharkie has sent copies of the reminders to your email!";

        assertCommandSuccess(new PeopleRemindAllCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        model.setUserData(getTypicalUserData());
        List<Person> lastShownList = model.getFilteredPersonList();

        String expectedMessage = "";
        for (Person p: lastShownList) {
            if (!p.getLoans().getTotal().isZero()) {
                expectedMessage += "Reminded " + p.getName().toString() + " to return "
                        + p.getLoans().getTotal().toString() + "!\n";
            }
        }
        expectedMessage += "Sharkie has sent copies of the reminders to your email!";

        assertCommandSuccess(new PeopleRemindAllCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_allPeopleWithZeroDebt_failure() {
        model.setUserData(getTypicalUserData());
        Person alice = new PersonBuilder(ALICE).withLoans().build();
        Person bob = new PersonBuilder(BOB).withLoans().build();
        Person carl = new PersonBuilder(CARL).withLoans().build();
        AddressBook ab = new AddressBookBuilder()
                .withPerson(alice)
                .withPerson(bob)
                .withPerson(carl).build();
        model.setAddressBook(ab);

        String expectedMessage = "No one owes you money :(";

        assertCommandFailure(new PeopleRemindAllCommand(), model, expectedMessage);
    }
}
