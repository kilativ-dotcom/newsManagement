package com.example.news.presentation.action;

import com.example.news.constants.JspConstants;
import com.example.news.exception.NewsNotFoundException;
import com.example.news.model.News;
import com.example.news.presentation.form.NewsForm;
import com.example.news.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping(path = JspConstants.SITE_BASENAME)
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(path = JspConstants.VIEW)
    public String viewAll(Map<String, Object> model){
        model.put(JspConstants.NEWS_LIST_ATTRIBUTE, newsService.getAll());
        return JspConstants.JSP_LIST;
    }

    @GetMapping(value = JspConstants.VIEW + "/{id}")
    public String viewOne(Map<String, Object> model, @PathVariable long id){
        News news = newsService.getById(id).orElseThrow(() -> new NewsNotFoundException(id));
        model.put(JspConstants.NEWS_ATTRIBUTE, news);
        return JspConstants.JSP_VIEW;
    }

    @GetMapping(path = JspConstants.ADD)
    public String startCreation(Map<String, Object> model){
        model.put(JspConstants.NEWS_FORM_ATTRIBUTE, new NewsForm());
        return JspConstants.JSP_EDIT;
    }

    @PostMapping(path = JspConstants.ADD)
    public String addNews(@Valid NewsForm newsForm, BindingResult bindingResult){
        if (!bindingResult.hasErrors()){
            News news = newsService.newsFromForm(newsForm);
            newsService.save(news);

            return ControllerUtils.redirect(JspConstants.SITE_BASENAME, JspConstants.VIEW, String.valueOf(news.getId()));
        } else {
            /*
                Invalid form
            */
            System.out.println("Errors:");
            bindingResult.getAllErrors().forEach(System.out::println);
            System.out.println("===============================\n\n");
            return JspConstants.JSP_EDIT;
        }
    }


    @GetMapping(path =  JspConstants.EDIT + "/{id}")
    public String startEdit(Map<String, Object> model, @PathVariable long id){
        News news = newsService.getById(id).orElseThrow(() -> new NewsNotFoundException(id));
        NewsForm newsForm = newsService.formFromNews(news);

        model.put(JspConstants.NEWS_FORM_ATTRIBUTE, newsForm);
        model.put(JspConstants.NEWS_ID_ATTRIBUTE, id);

        return JspConstants.JSP_EDIT;
    }

    @PostMapping(path = JspConstants.EDIT + "/{id}")
    public String endEdit(
            @RequestHeader String referer,
            @Valid NewsForm newsForm,
            BindingResult bindingResult,
            @PathVariable long id){
        if (bindingResult.hasErrors()){
            return JspConstants.JSP_EDIT;
        }

        String prefix = String.join("/", JspConstants.HOST, JspConstants.SITE_BASENAME, JspConstants.EDIT);
        if (String.join( "/",prefix, String.valueOf(id)).equals(referer)){
            Optional<News> optionalNews = newsService.getById(id);
            if (optionalNews.isPresent()) {
                News news = newsService.newsFromForm(newsForm);
                news.setId(id);
                newsService.save(news);

                return ControllerUtils.redirect(JspConstants.SITE_BASENAME, JspConstants.VIEW, String.valueOf(id));
            } else {
                /*
                    Wrong news id
                */
                System.out.println("Wrong news id:" + id);
                return ControllerUtils.redirect(JspConstants.SITE_BASENAME, JspConstants.VIEW);
            }
        } else {
            /*
                Illegal page accessing
            */
            System.out.println("Illegal page accessing");
            System.out.println("prefix/id  =" + prefix + "/" + id);
            System.out.println("referer    =" + referer);
            return ControllerUtils.redirect(JspConstants.SITE_BASENAME, JspConstants.VIEW);
        }
    }


    @GetMapping(path =  JspConstants.DELETE+ "/{id}")
    public String deleteOne(@PathVariable long id) {
        newsService.delete(id);

        return ControllerUtils.redirect(JspConstants.SITE_BASENAME, JspConstants.VIEW);
    }

    @PostMapping(path = JspConstants.DELETE)
    public String deleteMany(@RequestParam(required = false) long[] deleteNewsId) {
        newsService.delete(deleteNewsId);

        System.out.println("Deleted news:");
        System.out.println(Arrays.toString(deleteNewsId));
        System.out.println("=========================================\n\n");

        return ControllerUtils.redirect(JspConstants.SITE_BASENAME, JspConstants.VIEW);
    }

}
