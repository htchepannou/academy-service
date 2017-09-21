package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.QuizChoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizChoiceDao extends CrudRepository<QuizChoice, Integer> {
    List<QuizChoice> findByQuizId(Integer quizId);
}
