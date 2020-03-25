package seedu.address.model.transaction;

import java.util.List;

import seedu.address.commons.util.StringUtil;

public class TagContainsKeywordsPredicate  extends WalletPredicate{
    public TagContainsKeywordsPredicate(List<String> keywords) {
        super(keywords);
    }

    @Override
    public boolean test(Transaction transaction) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
                        transaction.getTag().tagName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AmountContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((AmountContainsKeywordsPredicate) other).keywords)); // state check
    }

}
