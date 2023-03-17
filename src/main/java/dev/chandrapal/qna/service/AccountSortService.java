package dev.chandrapal.qna.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.chandrapal.qna.entities.Account;
import dev.chandrapal.qna.service.impl.account.AccountSortType;

public interface AccountSortService {

    Page<Account> sort(Pageable pageable);

    boolean isSuitableFor(AccountSortType sortType);

}
