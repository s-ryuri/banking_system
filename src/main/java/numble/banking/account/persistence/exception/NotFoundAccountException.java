package numble.banking.account.persistence.exception;

import numble.banking.common.exception.NotFoundException;

public class NotFoundAccountException extends NotFoundException {

    public NotFoundAccountException() {
        super();
    }

    public NotFoundAccountException(final String message) {
        super(message);
    }

    public NotFoundAccountException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotFoundAccountException(final Throwable cause) {
        super(cause);
    }
}
