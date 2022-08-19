package com.example.news.presentation.action;

import com.example.news.constants.JspConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = JspConstants.LOCALE)
public class LocaleController {

    @GetMapping(path = JspConstants.CHANGE + "/{newLocale}")
    public String changeLocale(
            @PathVariable String newLocale,
            @RequestHeader(required = false) String referer,
            HttpSession session
    ){

        synchronized (session){
            session.setAttribute(JspConstants.LOCALE_ATTRIBUTE, newLocale);
        }


        if (null != referer) {
            String[] split = referer.split("://", 2);
            return ControllerUtils.redirect("/" + split[split.length-1]);
        } else {
            return ControllerUtils.redirect(JspConstants.SITE_BASENAME);
        }
    }

}

