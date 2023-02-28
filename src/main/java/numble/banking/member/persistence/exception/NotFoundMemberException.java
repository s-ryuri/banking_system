package numble.banking.member.persistence.exception;

import numble.banking.common.exception.NotFoundException;

public class NotFoundMemberException extends NotFoundException {
    public NotFoundMemberException() {
        super();
    }

    public NotFoundMemberException(final String message) {
        super(message);
    }

    public NotFoundMemberException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotFoundMemberException(final Throwable cause) {
        super(cause);
    }
}
