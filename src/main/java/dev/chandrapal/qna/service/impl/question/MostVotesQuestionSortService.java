package dev.chandrapal.qna.service.impl.question;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dev.chandrapal.qna.entities.Question;
import dev.chandrapal.qna.repository.QuestionRepository;
import dev.chandrapal.qna.service.QuestionSortService;

@Service
@AllArgsConstructor
public class MostVotesQuestionSortService implements QuestionSortService {

    private final QuestionRepository questionRepository;

    @Override
    public Page<Question> sort(Pageable pageable) {
        Pageable unsortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return questionRepository.findAllSortByMostVotes(unsortedPageable);
    }

    @Override
    public boolean isSuitableFor(QuestionSortType sortType) {
        return QuestionSortType.MOST_VOTES.equals(sortType);
    }

}
