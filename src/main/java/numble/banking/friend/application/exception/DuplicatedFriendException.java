package numble.banking.friend.application.exception;

import numble.banking.common.exception.DuplicatedException;

public class DuplicatedFriendException extends DuplicatedException {
    public DuplicatedFriendException() {
        super();
    }

    public DuplicatedFriendException(final String message) {
        super(message);
    }

    public DuplicatedFriendException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DuplicatedFriendException(final Throwable cause) {
        super(cause);
    }
}
