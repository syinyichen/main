package seedu.address.logic.commands.people;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Loan;
import seedu.address.testutil.PersonBuilder;

public class PeoplePaidCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_paidAll_success() {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person paidAllPerson = lastShownList.get(INDEX_SECOND_PERSON.getZeroBased());
        Amount originalAmount = paidAllPerson.getLoans().getTotal();
        Person removedLoanPerson = new PersonBuilder(paidAllPerson).withLoans().build();

        PeoplePaidCommand peoplePaidCommand = new PeoplePaidCommand(INDEX_SECOND_PERSON);

        String expectedMessage = String.format(PeoplePaidCommand.MESSAGE_PAID_SUCCESS,
                removedLoanPerson.getName(), originalAmount, removedLoanPerson.getLoans().getTotal());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), removedLoanPerson);

        assertCommandSuccess(peoplePaidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unfilteredList_paidByIndex_success() {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person paidPerson = lastShownList.get(INDEX_SECOND_PERSON.getZeroBased());
        Amount originalAmount = paidPerson.getLoans().getTotal();

        List<Loan> loans = paidPerson.getLoans().asUnmodifiableObservableList();
        Loan[] modifiedLoans = new Loan[loans.size() - 1];

        for (int i = 0; i < modifiedLoans.length; i++) {
            modifiedLoans[i] = loans.get(i + 1);
        }

        Person removedLoanPerson = new PersonBuilder(paidPerson).withLoans(modifiedLoans).build();

        PeoplePaidCommand peoplePaidCommand = new PeoplePaidCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(PeoplePaidCommand.MESSAGE_PAID_SUCCESS,
                removedLoanPerson.getName(), originalAmount, removedLoanPerson.getLoans().getTotal());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), removedLoanPerson);

        assertCommandSuccess(peoplePaidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {

        final PeoplePaidCommand standardCommand = new PeoplePaidCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);

        // same values -> returns true
        PeoplePaidCommand commandWithSameValues = new PeoplePaidCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new PeopleClearCommand()));

        // different person index -> returns false
        assertFalse(standardCommand.equals(new PeoplePaidCommand(INDEX_THIRD_PERSON, INDEX_FIRST_PERSON)));

        // different loan index -> returns false
        assertFalse(standardCommand.equals(new PeoplePaidCommand(INDEX_SECOND_PERSON, INDEX_SECOND_PERSON)));

        // no loan index -> returns false
        assertFalse(standardCommand.equals(new PeoplePaidCommand(INDEX_THIRD_PERSON)));
    }

}

