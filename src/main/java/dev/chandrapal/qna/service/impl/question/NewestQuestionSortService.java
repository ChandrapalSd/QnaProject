package dev.chandrapal.qna.service.impl.question;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.chandrapal.qna.entities.Question;
import dev.chandrapal.qna.repository.QuestionRepository;
import dev.chandrapal.qna.service.QuestionSortService;

import org.springframework.data.domain.Pageable;

@Service
@AllArgsConstructor
public class NewestQuestionSortService implements QuestionSortService {

    private final QuestionRepository questionRepository;

    @Override
    public Page<Question> sort(Pageable pageable) {
        Pageable unsortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize());
        return questionRepository.findAllSortByNewest(unsortedPageable);
    }

    @Override
    public boolean isSuitableFor(QuestionSortType sortType) {
        return  QuestionSortType.NEWEST.equals(sortType);
    }

}
