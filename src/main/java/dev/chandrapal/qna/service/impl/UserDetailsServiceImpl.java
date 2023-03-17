package dev.chandrapal.qna.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.chandrapal.qna.controller.exception.AccountNotFoundException;
import dev.chandrapal.qna.entities.Account;
import dev.chandrapal.qna.security.UserPrincipal;
import dev.chandrapal.qna.service.AccountService;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountService accountService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(email).orElseThrow(() -> new AccountNotFoundException(email));

        UserDetails userDetails = User.builder()
            .username(account.getEmail())
            .password(account.getPassword())
            .roles(account
                .getRoles()
                .stream()
                .map(Enum::toString)
                .toArray(String[]::new))
            .build();

        return new UserPrincipal(userDetails, account.getId());
    }

}
