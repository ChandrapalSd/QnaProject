package dev.chandrapal.qna.service.impl.account;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.chandrapal.qna.entities.Account;
import dev.chandrapal.qna.repository.AccountRepository;
import dev.chandrapal.qna.service.AccountSortService;

@Service
@AllArgsConstructor
public class IdAccountSortService implements AccountSortService {

    private final AccountRepository accountRepository;

    @Override
    public Page<Account> sort(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public boolean isSuitableFor(AccountSortType sortType) {
        return AccountSortType.ID.equals(sortType);
    }
}
