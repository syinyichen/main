package seedu.address.model.transaction;

import java.util.List;

public class DateContainsKeywordsPredicate extends WalletPredicate {
    public DateContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Transaction transaction) {
        return keywords.stream()
                .anyMatch(keyword -> keyword.equals(transaction.getDate().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AmountContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AmountContainsKeywordsPredicate) other).keywords)); // state check
    }
}
