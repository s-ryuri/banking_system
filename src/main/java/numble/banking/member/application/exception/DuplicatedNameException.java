package numble.banking.member.application.exception;

import numble.banking.common.exception.DuplicatedException;

public class DuplicatedNameException extends DuplicatedException {

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
