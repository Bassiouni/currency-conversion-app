package com.banquemisr.currencyconversionapp.exception;

import com.banquemisr.currencyconversionapp.entities.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The CustomExceptionHandler class handles exceptions of type BadEntryException, handleNotFoundException
 * and handleGenericException.
 * @author Menna Moataz
 */
@ControllerAdvice
public class CustomExceptionHandler {
    /**
     * The `@ExceptionHandler` annotation is used to handle specific exceptions thrown by methods in a
     * controller.
     * The function handles the BadEntryException by creating a response object with the exception
     * message and status code, and returning it as a ResponseEntity.
     * @param exception The "exception" parameter is an instance of the BadEntryException class, which
     * is thrown when a bad entry happens.
     * @return The method is returning a ResponseEntity object.
     */
    @ExceptionHandler(value = BadEntryException.class)
    public ResponseEntity<Object> badEntryException(BadEntryException exception) {
        Response<Object> response = Response.builder().message(exception.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .status(HttpStatus.BAD_REQUEST.name())
                .build();
        return ResponseEntity.status(response.statusCode()).body(response);
    }

    /**
     * The function handles the NotFoundException by creating a response object with the exception
     * message and status code, and returning it as a ResponseEntity.
     * @param exception The "exception" parameter is an instance of the NotFoundException class, which
     * is thrown when a resource is not found.
     * @return The method is returning a ResponseEntity object.
     */
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException exception) {
        Response<Object> response = Response
                .builder()
                .message(exception.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .status(HttpStatus.NOT_FOUND.name())
                .build();

        return ResponseEntity.status(response.statusCode()).body(response);
    }

    /**
     * The `@ExceptionHandler` annotation is used to handle specific exceptions thrown by methods in a
     * controller. In this case, the `handleGenericException` method is annotated with
     * `@ExceptionHandler(value = {Exception.class, Throwable.class})`, which means it will handle any
     * exception of type `Exception` or `Throwable`.
     * @param exception The "exception" parameter is an instance of the Exception class, which
     * is thrown when any exception happens.
     * @return The method is returning a ResponseEntity object.
     */
    @ExceptionHandler(value = {Exception.class, Throwable.class})
    public ResponseEntity<Object> handleGenericException(Exception exception) {
        Response<Object> response = Response
                .builder()
                .message("An unexpected error occurred")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .build();
        System.out.println(exception.getMessage());

        return ResponseEntity.status(response.statusCode()).body(response);
    }
}
