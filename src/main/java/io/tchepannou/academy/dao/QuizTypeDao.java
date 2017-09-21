package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.QuizType;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizTypeDao extends PersistentEnumRepository<QuizType> {
}
