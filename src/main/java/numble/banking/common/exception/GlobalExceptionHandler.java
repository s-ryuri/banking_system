package numble.banking.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException ex,
        final HttpHeaders headers,
        final HttpStatus status,
        final WebRequest request) {

        final List<String> errors = ex.getBindingResult()
                                      .getAllErrors()
                                      .stream()
                                      .map(DefaultMessageSourceResolvable::getDefaultMessage)
                                      .toList();

        return this.makeErrorResponseEntity(BAD_REQUEST, errors.toString());
    }

    private ResponseEntity<Object> makeErrorResponseEntity(final HttpStatus status, final String errorDescription) {
        return ResponseEntity.status(status)
                             .body(new ErrorResponse(status.toString(), errorDescription));
    }

    @ExceptionHandler(DuplicatedException.class)
    public ResponseEntity<?> handleDuplicatedException(Throwable e) {
        return makeErrorResponseEntity(BAD_REQUEST, e.getMessage());
    }

    @Getter
    @RequiredArgsConstructor
    static class ErrorResponse {
        private final String status;
        private final String message;
    }

}
