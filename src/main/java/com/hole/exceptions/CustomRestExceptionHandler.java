package com.hole.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.hole.dto.exceptions.ApiErrorDTO;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
  
  @ExceptionHandler({ HoleException.class })
  public ResponseEntity<ApiErrorDTO<String>> handleLojaException(HoleException ex, WebRequest request) {
    var apiError = new ApiErrorDTO<>(
        ex.getMessage(),
        "error no sistema",
        HttpStatus.INTERNAL_SERVER_ERROR);

    return new ResponseEntity<ApiErrorDTO<String>>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler({ EntityNotFoundException.class})
  public ResponseEntity<ApiErrorDTO<String>> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
    var apiError = new ApiErrorDTO<String>(
        ex.getMessage(),
        "recurso não encontrado",
        HttpStatus.NOT_FOUND);

    return new ResponseEntity<ApiErrorDTO<String>>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler({ PasswordRegisterException.class, EmailExistsException.class })
  public ResponseEntity<ApiErrorDTO<String>> handlePasswordRegister(HoleException ex, WebRequest request) {
    var apiError = new ApiErrorDTO<String>(
        ex.getMessage(),
        "error nos campos",
        HttpStatus.BAD_REQUEST);

    return new ResponseEntity<ApiErrorDTO<String>>(apiError, new HttpHeaders(), apiError.getStatus());
  }


  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) 
  {
    List<HashMap<String, String>> validationList = ex.getBindingResult().getFieldErrors()
      .stream()
      .map(fieldError -> new HashMap<String, String>() {{
        put(fieldError.getField(), fieldError.getDefaultMessage());
      }})
      .collect(Collectors.toList());

    var apiError = new ApiErrorDTO<List<HashMap<String, String>>>(
        "Error na entrada de dados",
        validationList,
        HttpStatus.BAD_REQUEST);

    return new ResponseEntity<>(apiError, status);
  }
}
