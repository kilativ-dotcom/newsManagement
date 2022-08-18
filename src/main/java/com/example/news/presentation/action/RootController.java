package com.example.news.presentation.action;

import com.example.news.constants.JspConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {


    @GetMapping
    public String redirectFromRoot(){
        return ControllerUtils.redirect(JspConstants.SITE_BASENAME);
    }

}
