package dev.chandrapal.qna.controller.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import static dev.chandrapal.qna.controller.ControllerConstants.ERROR_PAGE;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ModelAndView handleAccountNotFound(AccountNotFoundException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", exception.getMessage());
        ModelAndView model = new ModelAndView();
        model.addAllObjects(body);

        model.setViewName(ERROR_PAGE);
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("message", exception.getMessage());
        body.put("timestamp", LocalDateTime.now());
        ModelAndView model = new ModelAndView();
        model.addAllObjects(body);
        model.setViewName(ERROR_PAGE);
        return model;
    }


}
