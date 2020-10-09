package com.innovationchef.controller;

import com.innovationchef.constant.ApiConstant;
import com.innovationchef.constant.GameMessage;
import com.innovationchef.exception.*;
import com.innovationchef.model.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, handleException(ApiConstant.INVALID_PAYLOAD), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, handleException(ApiConstant.HANDLER_NOT_FOUND), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers,
                                                                      HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, handleException(ApiConstant.MEDIA_TYPE_INCORRECT), new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
                                                                         HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(ex, handleException(ApiConstant.MEDIA_TYPE_INCORRECT), new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED, request);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidSessionIdException.class})
    public ApiResponse handleInvalidSessionIdException(InvalidSessionIdException ex, WebRequest request) {
        log.error(ex);
        return handleException(ApiConstant.INVALID_SESSION);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {IllegalMoveException.class})
    public ApiResponse handleIllegalMoveException(IllegalMoveException ex, WebRequest request) {
        log.error(ex);
        return handleException(GameMessage.ILLEGAL_MOVE);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {ApiHeaderMissingException.class})
    public ApiResponse handleApiHeaderMissingException(InvalidSessionIdException ex, WebRequest request) {
        log.error(ex);
        return handleException(ApiConstant.MANDY_FIELDS_ABSENT);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MaxMovesReachedException.class})
    public ApiResponse handleApiHeaderMissingException(MaxMovesReachedException ex, WebRequest request) {
        log.error(ex);
        return handleException(GameMessage.GAME_OVER);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {InvalidMoveException.class})
    public ApiResponse handleApiHeaderMissingException(InvalidMoveException ex, WebRequest request) {
        log.error(ex);
        return handleException(GameMessage.INVALID_MOVE);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class})
    public ApiResponse handleGenericException(Exception ex, WebRequest request) {
        log.error(ex);
        return handleException(ApiConstant.INTERNAL_SERVER_ERROR);
    }

    private ApiResponse handleException(String apiMessage) {
        return ApiResponse.builder()
                .success(false)
                .message(apiMessage)
                .build();
    }
}
