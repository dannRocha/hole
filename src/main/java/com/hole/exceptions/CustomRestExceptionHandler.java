package com.hole.exceptions;

import com.hole.dto.exceptions.ApiErrorDTO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler({HoleException.class})
  public ResponseEntity<ApiErrorDTO> handleLojaException(HoleException ex, WebRequest request) {
    var apiError = new ApiErrorDTO(
      ex.getMessage(), 
      "error no sistema", 
      HttpStatus.INTERNAL_SERVER_ERROR
    );

    return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler({EntityNotFoundException.class})
  public ResponseEntity<ApiErrorDTO> handleEntiNotFound(EntityNotFoundException ex, WebRequest request) {
    var apiError = new ApiErrorDTO(
      ex.getMessage(), 
      "recurso não encontrado", 
      HttpStatus.NOT_FOUND
    );
    
    return new ResponseEntity<ApiErrorDTO>(apiError, new HttpHeaders(), apiError.getStatus());
  }
}
