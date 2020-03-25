package seedu.address.logic.commands.wallet;

import static seedu.address.logic.parser.CliPrefix.WALLET_COMMAND_TYPE;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.transaction.WalletPredicate;

/**
 * Finds and lists all transactions in wallet book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class WalletFindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all transactions with description containing "
            + "any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + WALLET_COMMAND_TYPE + " "
            + COMMAND_WORD + " rice water chicken";

    public static final String NO_PARAMETER_INPUTTED = "At least one field to find must be provided.";

    private final WalletPredicate predicate;

    public WalletFindCommand(WalletPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTransactionList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_TRANSACTIONS_LISTED_OVERVIEW,
                        model.getFilteredTransactionList().size()));
    }

    private void requireNonNull(Model model) {
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WalletFindCommand // instanceof handles nulls
                && predicate.equals(((WalletFindCommand) other).predicate)); // state check
    }

    /**
     * Stores the details to find the Transaction with. Each non-empty field value will replace the
     * corresponding field value of the transaction.
     */
    /*
    public static class FindTransactionDescriptor {
        private Description description;
        private Amount amount;
        private Date date;
        private Tag tag;

        public FindTransactionDescriptor() {
        }


        public FindTransactionDescriptor(FindTransactionDescriptor toCopy) {
            setDescription(toCopy.description);
            setAmount(toCopy.amount);
            setDate(toCopy.date);
            setTag(toCopy.tag);
        }

        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(description, amount, date, tag);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setAmount(Amount amount) {
            this.amount = amount;
        }

        public Optional<Amount> getAmount() {
            return Optional.ofNullable(amount);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }


        public void setTag(Tag tag) {
            this.tag = tag;
        }

        public Optional<Tag> getTag() {
            return Optional.ofNullable(tag);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof FindTransactionDescriptor)) {
                return false;
            }

            // state check
            FindTransactionDescriptor e = (FindTransactionDescriptor) other;

            return getDescription().equals(e.getDescription())
                    && getAmount().equals(e.getAmount())
                    && getDate().equals(e.getDate())
                    && getTag().equals(e.getTag());
        }
    }
    */
}
