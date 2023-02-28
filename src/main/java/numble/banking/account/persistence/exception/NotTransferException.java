package numble.banking.account.persistence.exception;

public class NotTransferException extends RuntimeException {
    public NotTransferException() {
        super();
    }

    public NotTransferException(final String message) {
        super(message);
    }

    public NotTransferException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotTransferException(final Throwable cause) {
        super(cause);
    }
}
