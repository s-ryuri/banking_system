package numble.banking.common.exception;

public class DuplicatedException extends RuntimeException {
    public DuplicatedException() {
        super();
    }

    public DuplicatedException(final String message) {
        super(message);
    }

    public DuplicatedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DuplicatedException(final Throwable cause) {
        super(cause);
    }
}
