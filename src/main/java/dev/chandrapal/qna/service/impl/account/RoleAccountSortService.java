package dev.chandrapal.qna.service.impl.account;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.chandrapal.qna.entities.Account;
import dev.chandrapal.qna.entities.Role;
import dev.chandrapal.qna.repository.AccountRepository;
import dev.chandrapal.qna.service.AccountSortService;

@Service
@AllArgsConstructor
public class RoleAccountSortService implements AccountSortService {

    private final AccountRepository accountRepository;

    @Override
    public Page<Account> sort(Pageable pageable) {
        Pageable unsortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return accountRepository.findAllByRole(Role.MODERATOR.toString(), unsortedPageable);
    }

    @Override
    public boolean isSuitableFor(AccountSortType sortType) {
        return AccountSortType.MODERATORS.equals(sortType);
    }
}
