package github.kanwalnain.creditcard.exception;

import github.kanwalnain.creditcard.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Exception handler to validate data inputs from user.
 */
@ControllerAdvice
public class DataInputExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final MethodArgumentNotValidException ex) {

        List list = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> "["+fieldError.getField() + ":" +fieldError.getRejectedValue() +"]"+fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = new ApiError(list.toString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final Exception ex) {

        ApiError apiError = new ApiError("Error while performing operation.", HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
