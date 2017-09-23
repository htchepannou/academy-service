package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;

import java.util.List;
import java.util.stream.Collectors;

public class MultiChoiceAnswerValidator implements AnswerValidator {
    @Override
    public boolean isValid(final List<String> answers, final Quiz quiz, final List<QuizChoice> choices) {
        final List<QuizChoice> choiceAnswers = choices.stream()
                .filter(choice -> choice.isAnswer())
                .collect(Collectors.toList());
        if (answers.size() != choiceAnswers.size()){
            return false;
        }

        int count = 0;
        for (final QuizChoice choice : choiceAnswers){
            final String answer = choice.getId().toString();
            if (answers.contains(answer)){
                count++;
            }
        }

        return count == choiceAnswers.size();
    }
}
