package com.jwdev.ChallengePicPay.infra.Exception;


import com.jwdev.ChallengePicPay.dtos.ExceptionDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDuplicateEntry(DataIntegrityViolationException exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO("User already exists", "404");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }



//    @ExceptionHandler(EntityNot)
//    public ResponseEntity threat404(DataIntegrityViolationException exception) {
//        return ResponseEntity.notFound().build();
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threat500(Exception exception) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
