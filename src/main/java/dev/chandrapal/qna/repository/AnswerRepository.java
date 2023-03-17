package dev.chandrapal.qna.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.chandrapal.qna.entities.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
