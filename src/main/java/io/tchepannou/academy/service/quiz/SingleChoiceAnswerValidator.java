package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;

import java.util.List;

public class SingleChoiceAnswerValidator implements AnswerValidator {
    @Override
    public boolean isValid(final List<String> answers, final Quiz quiz, final List<QuizChoice> choices) {
        if (answers.size() != 1){
            return false;
        }

        final String answer = answers.get(0);
        return choices.stream()
                .filter(choice -> choice.getId().toString().equals(answer) && choice.isAnswer())
                .findFirst()
                .isPresent();
    }
}
