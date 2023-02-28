package numble.banking.account.application.exception;

public class NotFriendException extends RuntimeException {
    public NotFriendException() {
        super();
    }

    public NotFriendException(final String message) {
        super(message);
    }

    public NotFriendException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotFriendException(final Throwable cause) {
        super(cause);
    }
}
