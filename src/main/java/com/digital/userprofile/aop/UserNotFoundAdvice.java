package com.digital.userprofile.aop;

import com.digital.userprofile.exception.UserNotFoundException;
import com.digital.userprofile.pojo.requestmodel.Error;
import com.digital.userprofile.pojo.requestmodel.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class UserNotFoundAdvice {

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  Error UnexpectedErrorHandler(UserNotFoundException ex) {

    return Error.builder()
            .error(ErrorCode.BG2000.getCode())
            .message(ErrorCode.BG2000.getMessage())
            .build();
  }
}