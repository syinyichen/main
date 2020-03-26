package seedu.address.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.TransactionBuilder;

public class AmountContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("1.33");
        List<String> secondPredicateKeywordList = Arrays.asList("1.54", "1.22");

        AmountContainsKeywordsPredicate firstPredicate =
                new AmountContainsKeywordsPredicate(firstPredicateKeywordList);
        AmountContainsKeywordsPredicate secondPredicate =
                new AmountContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AmountContainsKeywordsPredicate firstPredicateCopy =
                new AmountContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }


    @Test
    public void test_amountContainsKeywordsExpenses_returnsTrue() {
        // One keyword
        AmountContainsKeywordsPredicate predicate =
                new AmountContainsKeywordsPredicate(Collections.singletonList("1.22"));
        assertTrue(predicate.test(new TransactionBuilder().withAmount("1.56").buildExpense()));

        // Multiple keywords
        predicate = new AmountContainsKeywordsPredicate(Arrays.asList("1", "1.35"));
        assertTrue(predicate.test(new TransactionBuilder().withAmount("1.33").buildExpense()));

        // Only one matching keyword
        predicate = new AmountContainsKeywordsPredicate(Arrays.asList("1.2", "9.11"));
        assertTrue(predicate.test(new TransactionBuilder().withAmount("1.55").buildExpense()));
    }

    @Test
    public void test_nameContainsKeywordsIncome_returnsTrue() {
        // One keyword
        AmountContainsKeywordsPredicate predicate =
                new AmountContainsKeywordsPredicate(Collections.singletonList("1.22"));
        assertTrue(predicate.test(new TransactionBuilder().withAmount("1.56").buildIncome()));

        // Multiple keywords
        predicate = new AmountContainsKeywordsPredicate(Arrays.asList("1", "1.35"));
        assertTrue(predicate.test(new TransactionBuilder().withAmount("1.33").buildIncome()));

        // Only one matching keyword
        predicate = new AmountContainsKeywordsPredicate(Arrays.asList("1.2", "9.11"));
        assertTrue(predicate.test(new TransactionBuilder().withAmount("1.55").buildIncome()));
    }

    @Test
    public void test_nameDoesNotContainKeywordsExpense_returnsFalse() {
        // Zero keywords
        AmountContainsKeywordsPredicate predicate =
                new AmountContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TransactionBuilder().withAmount("1.33").buildExpense()));

        // Non-matching keyword
        predicate = new AmountContainsKeywordsPredicate(Arrays.asList("1.34"));
        assertFalse(predicate.test(new TransactionBuilder().withAmount("3.55").buildExpense()));
    }


    @Test
    public void test_nameDoesNotContainKeywordsIncome_returnsFalse() {
        // Zero keywords
        AmountContainsKeywordsPredicate predicate =
                new AmountContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TransactionBuilder().withAmount("1.22").buildIncome()));

        // Non-matching keyword
        predicate = new AmountContainsKeywordsPredicate(Arrays.asList("6.44"));
        assertFalse(predicate.test(new TransactionBuilder().withAmount("1.33").buildIncome()));

    }
}
