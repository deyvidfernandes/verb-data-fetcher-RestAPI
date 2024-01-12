package br.com.deyvidfernandes.verbDataFetcher;


import br.com.deyvidfernandes.verbDataFetcher.verb.FailPersistingVerbsException;

import com.zaxxer.hikari.pool.HikariPool;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import org.json.JSONObject;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(HikariPool.PoolInitializationException.class)
    public ResponseEntity<Object> handleInvalidDatasourceParamsException(HikariPool.PoolInitializationException ex, ServletWebRequest request) {
        return responseBodyBuilder(
                HttpStatus.BAD_REQUEST,
                "invalid configuration parameters",
                request,
                ex
        );
    }

    @ExceptionHandler(FailPersistingVerbsException.class)
    public ResponseEntity<Object> handleFailPersistingVerbsException(FailPersistingVerbsException ex, ServletWebRequest request) {
        return responseBodyBuilder(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An error occurred while persisting verb data",
                request,
                ex
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex, ServletWebRequest request) {
        return responseBodyBuilder(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unknown error occurred",
                request,
                ex
        );
    }

    private ResponseEntity<Object> responseBodyBuilder(HttpStatus httpStatus, String message, ServletWebRequest request, Exception ex) {
        JSONObject bodyOfResponse = new JSONObject();
        bodyOfResponse.put("status", httpStatus.value());
        bodyOfResponse.put("message", message);
        bodyOfResponse.put("exceptionMessage", ex.getMessage());
        bodyOfResponse.put("path", request.getRequest().getRequestURI());
        return ResponseEntity.status(httpStatus).body(bodyOfResponse.toString());
    }

}
