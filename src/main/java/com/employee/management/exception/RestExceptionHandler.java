package com.employee.management.exception;

import com.employee.management.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

   @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ResponseDto> entityNotFound(EntityNotFoundException ex, WebRequest request) {
        log.debug("handling EntityNotFoundException...");
       return ResponseEntity.ok(ResponseDto
                .builder()
                .message(ex.getMessage ())
                .status(false).build());
    }



}
