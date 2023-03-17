package dev.chandrapal.qna.controller.web;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.chandrapal.qna.controller.validator.AccountPostDtoValidator;
import dev.chandrapal.qna.dto.AccountPostDto;
import dev.chandrapal.qna.dto.mapper.AccountMapper;
import dev.chandrapal.qna.entities.Account;
import dev.chandrapal.qna.service.AccountService;

import static dev.chandrapal.qna.controller.ControllerConstants.REGISTRATION_PATH;

import java.util.Locale;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping(REGISTRATION_PATH)
public class RegistrationController {

    private final AccountService accountService;
    private final AccountMapper accountMapper;
    private final AccountPostDtoValidator accountPostDtoValidator;
    private final MessageSource messageSource;

    @ModelAttribute("module")
    String module() {
        return "registration";
    }

    @GetMapping
    public String registration(AccountPostDto accountPostDto) {
        return "registration";
    }

    @PostMapping
    public String registration(@Validated @ModelAttribute("accountPostDto") AccountPostDto accountPostDto,
                               BindingResult bindingResult,
                               Model model,
                               Locale locale) {

        accountPostDtoValidator.validate(accountPostDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        Optional<Account> account = accountService.findByEmail(accountPostDto.getEmail());

        if (account.isPresent()) {
            String errorMessage = messageSource.getMessage("error.registration.user.exist", null, locale);
            model.addAttribute("error", errorMessage);
            return "registration";
        }

        accountService.save(accountMapper.postDtoToAccount(accountPostDto));

        return "redirect:login";
    }



}
