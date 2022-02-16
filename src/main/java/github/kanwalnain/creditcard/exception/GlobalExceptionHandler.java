package github.kanwalnain.creditcard.exception;

import com.fasterxml.jackson.core.JsonParseException;
import github.kanwalnain.creditcard.model.ApiError;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
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
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final MethodArgumentNotValidException ex) {

        List list = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> "["+fieldError.getField() + ":" +fieldError.getRejectedValue() +"]"+fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        ApiError apiError = new ApiError( list.toString(), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final DataIntegrityViolationException ex) {

        ApiError apiError = new ApiError("Credit card already registered.",  HttpStatus.CONFLICT );
        return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final DataAccessException ex) {

        ApiError apiError = new ApiError("Unable to process data in your request invalid request.",  HttpStatus.INTERNAL_SERVER_ERROR );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final HttpMessageNotReadableException ex) {

        ApiError apiError = new ApiError("Bad data input please validate your request.",  HttpStatus.BAD_REQUEST );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiError> processUnmergeException(final Exception ex) {

        ApiError apiError = new ApiError("Unexpected error your request can not be processed. ",  HttpStatus.INTERNAL_SERVER_ERROR );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
