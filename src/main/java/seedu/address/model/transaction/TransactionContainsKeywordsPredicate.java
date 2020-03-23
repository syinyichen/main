package seedu.address.model.transaction;

import java.util.function.Predicate;

import seedu.address.logic.commands.wallet.WalletFindCommand.FindTransactionDescriptor;

public class TransactionContainsKeywordsPredicate implements Predicate<Transaction> {
    FindTransactionDescriptor findTransactionDescriptor;

    public TransactionContainsKeywordsPredicate(FindTransactionDescriptor findTransactionDescriptor) {
        this.findTransactionDescriptor = findTransactionDescriptor;
    }

    @Override
    public boolean test(Transaction transaction) {
//        return keywords.stream()
//                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(
//                        transaction.getDescription().description, keyword));
        boolean haveMatch = false;
        if (findTransactionDescriptor.getDescription() != null) {
            boolean isSame = transaction.getDescription().equals(findTransactionDescriptor.getDescription());
            if (isSame) {
                haveMatch = true;
            } else {
                return false;
            }
        }

        if (findTransactionDescriptor.getAmount() != null) {
            boolean isSame = transaction.getAmount().equals(findTransactionDescriptor.getAmount());
            if (isSame) {
                haveMatch = true;
            } else {
                return false;
            }
        }

        if (findTransactionDescriptor.getDate() != null) {
            boolean isSame = transaction.getDate().equals(findTransactionDescriptor.getDate());
            if (isSame) {
                haveMatch = true;
            } else {
                return false;
            }
        }

        if (findTransactionDescriptor.getTag() != null) {
            boolean isSame = transaction.getTag().equals(findTransactionDescriptor.getTag());
            if (isSame) {
                haveMatch = true;
            } else {
                return false;
            }
        }

        return haveMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TransactionContainsKeywordsPredicate // instanceof handles nulls
                && findTransactionDescriptor.equals(((TransactionContainsKeywordsPredicate) other).findTransactionDescriptor)); // state check
    }
}
