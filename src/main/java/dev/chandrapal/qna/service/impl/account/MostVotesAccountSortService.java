package dev.chandrapal.qna.service.impl.account;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.chandrapal.qna.entities.Account;
import dev.chandrapal.qna.repository.AccountRepository;
import dev.chandrapal.qna.service.AccountSortService;

@Service
@AllArgsConstructor
public class MostVotesAccountSortService implements AccountSortService {

    private final AccountRepository accountRepository;

    public Page<Account> sort(Pageable pageable) {
        Pageable unsortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return accountRepository.findAllSortByMostVotes(unsortedPageable);
    }

    public boolean isSuitableFor(AccountSortType sortType) {
        return AccountSortType.MOST_VOTES.equals(sortType);
    }

}
