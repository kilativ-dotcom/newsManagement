package com.example.news.presentation.action;

import com.example.news.constants.JspConstants;
import com.example.news.exception.NewsNotFoundException;
import com.example.news.model.News;
import com.example.news.presentation.form.NewsForm;
import com.example.news.service.NewsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping(path = JspConstants.SITE_BASENAME)
public class NewsController {

    private static final Logger LOGGER = LogManager.getLogger(NewsController.class);

    private NewsService newsService;

    @Autowired
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping()
    public String viewAll(Map<String, Object> model) {
        model.put(
                JspConstants.NEWS_LIST_ATTRIBUTE,
                newsService.getAll()
        );
        return JspConstants.JSP_LIST;
    }

    @GetMapping(value = "/{id}")
    public String viewOne(
            Map<String, Object> model,
            @PathVariable long id
    ) {
        News news = newsService.getById(id).orElseThrow(
                () -> new NewsNotFoundException(id)
        );
        model.put(JspConstants.NEWS_ATTRIBUTE, news);
        return JspConstants.JSP_VIEW;
    }

    @GetMapping(path = JspConstants.ADD)
    public String startAdd(Map<String, Object> model) {
        model.put(JspConstants.NEWS_FORM_ATTRIBUTE, new NewsForm());
        return JspConstants.JSP_EDIT;
    }

    @PostMapping(path = JspConstants.ADD)
    public String endAdd(@Valid NewsForm newsForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            News news = newsService.newsFromForm(newsForm);
            news = newsService.save(news);

            return ControllerUtils.redirect(JspConstants.SITE_BASENAME, String.valueOf(news.getId()));
        } else {
            /*
                Invalid form
            */
            bindingResult.getAllErrors().forEach(LOGGER::warn);
            return JspConstants.JSP_EDIT;
        }
    }


    @GetMapping(path = JspConstants.EDIT + "/{id}")
    public String startEdit(Map<String, Object> model, @PathVariable long id) {
        News news = newsService.getById(id).orElseThrow(() -> new NewsNotFoundException(id));
        NewsForm newsForm = newsService.formFromNews(news);

        model.put(JspConstants.NEWS_FORM_ATTRIBUTE, newsForm);
        model.put(JspConstants.NEWS_ID_ATTRIBUTE, id);

        return JspConstants.JSP_EDIT;
    }

    @PostMapping(path = JspConstants.EDIT + "/{id}")
    public String endEdit(
            @Valid NewsForm newsForm,
            BindingResult bindingResult,
            @PathVariable long id) {
        if (bindingResult.hasErrors()) {
            return JspConstants.JSP_EDIT;
        }

        newsService.getById(id).orElseThrow(() -> new NewsNotFoundException(id));

        News news = newsService.newsFromForm(newsForm);
        news.setId(id);
        newsService.save(news);

        return ControllerUtils.redirect(JspConstants.SITE_BASENAME, String.valueOf(id));
    }


    @GetMapping(path = JspConstants.DELETE + "/{id}")
    public String deleteOne(@PathVariable long id) {
        LOGGER.info("Deleting news with id " + id);
        newsService.delete(id);

        return ControllerUtils.redirect(JspConstants.SITE_BASENAME);
    }

    @PostMapping(path = JspConstants.DELETE)
    public String deleteMany(@RequestParam(required = false) long[] deleteNewsId) {
        LOGGER.info("Deleting news with ids " + Arrays.toString(deleteNewsId));
        newsService.delete(deleteNewsId);

        return ControllerUtils.redirect(JspConstants.SITE_BASENAME);
    }

    @RequestMapping("/**")
    public String unmappedResource() {
        return JspConstants.JSP_404;
    }

}
