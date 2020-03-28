package seedu.address.model.transaction;

import java.util.List;

/**
 * Tests that a {@code Transaction} matches any of the keywords given.
 */
public class AmountContainsKeywordsPredicate extends WalletPredicate {

    public AmountContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Transaction transaction) {
        return keywords.stream()
                .anyMatch(keyword -> (int) Double.parseDouble(keyword)
                        == (int) transaction.getAmount().amount);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AmountContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AmountContainsKeywordsPredicate) other).keywords)); // state check
    }
}
