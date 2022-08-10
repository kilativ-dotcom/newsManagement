package com.example.news.presentation.action;

import com.example.news.constants.JspConstants;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "errors")
public class SimpleErrorController /*implements ErrorController*/ {





    @RequestMapping(path = "${spring.datasource.username:usrn}")
    public String processError() {
        System.out.println("in process error");
        return JspConstants.JSP_404;
    }

    @RequestMapping(path = "${spring.datasource.username2:usrn2}")
    public String processError2() {
        System.out.println("in process error2");
        return JspConstants.JSP_404;
    }
}
