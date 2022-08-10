package com.example.news.advice;

import com.example.news.constants.JspConstants;
import com.example.news.exception.NewsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@ControllerAdvice
public class NewsNotFoundAdvice {

    @ExceptionHandler(NewsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String newsNotFound(NewsNotFoundException exception, WebRequest request){
        request.setAttribute(JspConstants.NEWS_ID_ATTRIBUTE, exception.getId(), WebRequest.SCOPE_REQUEST);
        return JspConstants.JSP_404;
    }
}
