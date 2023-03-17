package dev.chandrapal.qna.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import dev.chandrapal.qna.entities.Question;
import dev.chandrapal.qna.service.impl.question.QuestionSortType;

public interface QuestionSortService {

    Page<Question> sort(Pageable pageable);

    boolean isSuitableFor(QuestionSortType sortType);

}
