package com.eainde.ddd.application.controller;

import com.eainde.ddd.application.controller.dto.ExceptionResponseDto;
import com.eainde.ddd.application.controller.dto.ImmutableExceptionResponseDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {
  private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlingController.class);

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponseDto> generalException(Exception ex) {
    ExceptionResponseDto dto =
        ImmutableExceptionResponseDto.builder()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .description(ex.getMessage())
            .build();
    if (LOGGER.isErrorEnabled()) {
      LOGGER.error(String.format("GeneralException : %s", ex.getMessage()), ex);
    }
    return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
