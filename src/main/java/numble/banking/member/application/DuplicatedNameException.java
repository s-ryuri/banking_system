package numble.banking.member.application;

public class DuplicatedNameException extends RuntimeException {

    public DuplicatedNameException() {
        super();
    }

    public DuplicatedNameException(final String message) {
        super(message);
    }

    public DuplicatedNameException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DuplicatedNameException(final Throwable cause) {
        super(cause);
    }
}
