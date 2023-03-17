package dev.chandrapal.qna.controller.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.chandrapal.qna.entities.Question;
import dev.chandrapal.qna.entities.Tag;
import dev.chandrapal.qna.service.QuestionService;
import dev.chandrapal.qna.service.TagService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {

    private final QuestionService questionService;
    private final TagService tagService;

    @ModelAttribute("module")
    public String module() {
        return "index";
    }

    @GetMapping
    public String index(Model model) {
        List<Question> questions = questionService.findAll();

        int tagsCount = 20;
        List<Tag> tags = tagService
                .findAll()
                .stream()
                .limit(tagsCount)
                .collect(Collectors.toList());

        model.addAttribute("tags", tags);
        model.addAttribute("questions", questions);

        return "index";
    }
    
    @GetMapping("/about")
    @ModelAttribute("module")
    public String about(Model model) {
        return "about";
    }
    
    @GetMapping("/contact")
    @ModelAttribute("module")
    public String contact(Model model) {
        return "contact";
    }

}
