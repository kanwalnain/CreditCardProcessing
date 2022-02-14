package github.kanwalnain.creditcard.model;

import org.springframework.http.HttpStatus;

public class ApiError  extends RuntimeException{

  private String errorMessage;
  private HttpStatus httpStatus;

    public ApiError(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "errorMessage='" + errorMessage + '\'' +
                ", httpStatus=" + httpStatus +
                '}';
    }
}
