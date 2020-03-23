package seedu.address.model.transaction;

import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.wallet.WalletFindCommand.FindTransactionDescriptor;

public class TransactionContainsKeywordsPredicate implements Predicate<Transaction> {

    private static final Logger logger = LogsCenter.getLogger(TransactionContainsKeywordsPredicate.class);
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
        if (findTransactionDescriptor.getDescription().isPresent()) {
            boolean isSame = transaction.getDescription().description.toLowerCase().contains(
                    findTransactionDescriptor.getDescription().get().description.toLowerCase());
            logger.info("desc");
            logger.info( transaction.getDescription().description + " and " + findTransactionDescriptor.getDescription().get().description);
            if (isSame) {
                haveMatch = true;
                logger.info("desc true");
            } else {
                logger.info("desc false");
                return false;
            }
        }

        if (findTransactionDescriptor.getAmount().isPresent()) {
            boolean isSame = transaction.getAmount().equals(findTransactionDescriptor.getAmount().get().amount);
            logger.info("amt");
            if (isSame) {
                haveMatch = true;
                logger.info("amt true");
            } else {
                logger.info("amt false");
                return false;
            }
        }

        if (findTransactionDescriptor.getDate().isPresent()) {
            boolean isSame = transaction.getDate().equals(findTransactionDescriptor.getDate().get().getDate());
            logger.info("date");
            if (isSame) {
                haveMatch = true;
                logger.info("date true");
            } else {
                logger.info("date false");
                return false;
            }
        }

        if (findTransactionDescriptor.getTag().isPresent()) {
            boolean isSame = transaction.getTag().equals(findTransactionDescriptor.getTag().get().tagName);
            logger.info("tag");
            if (isSame) {
                haveMatch = true;
                logger.info("tag true");
            } else {
                logger.info("tag false");
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
