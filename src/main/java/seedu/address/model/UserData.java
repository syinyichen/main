package seedu.address.model;

import seedu.address.model.person.User;

/**
 * Wraps all data of the user
 */
public class UserData implements ReadOnlyUserData {

    private final User user;

    public UserData(User user) {
        this.user = user;
    }

    /**
     * Creates an UserData using the data in the {@code toBeCopied}
     */
    public UserData(ReadOnlyUserData toBeCopied) {
        this(toBeCopied.getUser());
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserData // instanceof handles nulls
                && user.equals(((UserData) other).user));
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }
}
