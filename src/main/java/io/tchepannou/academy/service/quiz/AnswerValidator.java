package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;

import java.util.List;

public interface AnswerValidator {
    boolean isValid(List<String> answers, Quiz quiz, List<QuizChoice> choices);
}
