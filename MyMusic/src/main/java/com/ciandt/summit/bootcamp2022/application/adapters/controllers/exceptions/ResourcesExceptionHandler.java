package com.ciandt.summit.bootcamp2022.application.adapters.controllers.exceptions;

import com.ciandt.summit.bootcamp2022.exceptions.InvalidParameterException;
import com.ciandt.summit.bootcamp2022.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourcesExceptionHandler {
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<StandardError> invalidParameterException(InvalidParameterException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Solicitação inválida");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
