package seedu.address.logic.commands.people;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.transaction.Amount;
import seedu.address.model.transaction.Loan;
import seedu.address.testutil.PersonBuilder;

public class PeopleReceivedCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredListReceivedAll_success() {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person receivedAllPerson = lastShownList.get(INDEX_SECOND_PERSON.getZeroBased());
        Amount originalAmount = receivedAllPerson.getLoans().getTotal();
        Person removedLoanPerson = new PersonBuilder(receivedAllPerson).withLoans().build();

        PeopleReceivedCommand peoplePaidCommand = new PeopleReceivedCommand(INDEX_SECOND_PERSON, null);

        String expectedMessage = String.format(PeopleReceivedCommand.MESSAGE_RECEIVED_SUCCESS,
                removedLoanPerson.getName(), originalAmount, removedLoanPerson.getLoans().getTotal());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), removedLoanPerson);

        assertCommandSuccess(peoplePaidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_unfilteredListReceivedByIndex_success() {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person receivedPerson = lastShownList.get(INDEX_SECOND_PERSON.getZeroBased());

        List<Loan> loans = receivedPerson.getLoans().asUnmodifiableObservableList();
        Loan[] modifiedLoans = new Loan[loans.size() - 1];

        for (int i = 0; i < modifiedLoans.length; i++) {
            modifiedLoans[i] = loans.get(i + 1);
        }

        Person removedLoanPerson = new PersonBuilder(receivedPerson).withLoans(modifiedLoans).build();

        PeopleReceivedCommand peoplePaidCommand = new PeopleReceivedCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(PeopleReceivedCommand.MESSAGE_RECEIVED_SUCCESS,
                removedLoanPerson.getName(), loans.get(0).getAmount(), removedLoanPerson.getLoans().getTotal());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), removedLoanPerson);

        assertCommandSuccess(peoplePaidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredListReceivedAll_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PeopleReceivedCommand peopleReceivedCommand = new PeopleReceivedCommand(outOfBoundIndex, null);
        assertCommandFailure(peopleReceivedCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredListReceivedByIndex_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PeopleReceivedCommand peopleReceivedCommand = new PeopleReceivedCommand(outOfBoundIndex, INDEX_FIRST_PERSON);
        assertCommandFailure(peopleReceivedCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidLoanIndexUnfilteredListReceivedByIndex_failure() {
        List<Person> lastShownList = model.getFilteredPersonList();
        Person receivedPerson = lastShownList.get(INDEX_SECOND_PERSON.getZeroBased());
        Index outOfBoundIndex = Index.fromOneBased(receivedPerson.getLoans()
                .asUnmodifiableObservableList().size() + 1);
        PeopleReceivedCommand peopleReceivedCommand = new PeopleReceivedCommand(INDEX_SECOND_PERSON, outOfBoundIndex);
        assertCommandFailure(peopleReceivedCommand, model, Messages.MESSAGE_INVALID_LOAN_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {

        final PeopleReceivedCommand standardCommand =
                new PeopleReceivedCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);

        // same values -> returns true
        PeopleReceivedCommand commandWithSameValues =
                new PeopleReceivedCommand(INDEX_SECOND_PERSON, INDEX_FIRST_PERSON);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new PeopleClearCommand()));

        // different person index -> returns false
        assertFalse(standardCommand.equals(new PeopleReceivedCommand(INDEX_THIRD_PERSON, INDEX_FIRST_PERSON)));

        // different loan index -> returns false
        assertFalse(standardCommand.equals(new PeopleReceivedCommand(INDEX_SECOND_PERSON, INDEX_SECOND_PERSON)));

        // no loan index -> returns false
        assertFalse(standardCommand.equals(new PeopleReceivedCommand(INDEX_THIRD_PERSON, null)));
    }

}

