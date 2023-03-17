package dev.chandrapal.qna.service;

import java.util.List;
import java.util.Optional;

import dev.chandrapal.qna.entities.Answer;

public interface AnswerService {

    List<Answer> findAll();

    Optional<Answer> findById(Long id);

    Answer save(Answer answer);

    void deleteById(Long id);

}
