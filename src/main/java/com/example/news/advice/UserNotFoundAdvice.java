package com.example.news.advice;

import com.example.news.constants.JspConstants;
import com.example.news.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class UserNotFoundAdvice {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String newsNotFound(UserNotFoundException exception, WebRequest request){
        request.setAttribute(JspConstants.USER_ATTRIBUTE, exception.getUsername(), WebRequest.SCOPE_REQUEST);
        return JspConstants.JSP_404;
    }
}
