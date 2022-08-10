package com.example.news.presentation.action;

public class ControllerUtils {
    public static String redirect(CharSequence... path){
        return "redirect:/" + String.join("/", path);
    }
}
