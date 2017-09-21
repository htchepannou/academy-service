package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDao extends CrudRepository<Quiz, Integer> {
}
