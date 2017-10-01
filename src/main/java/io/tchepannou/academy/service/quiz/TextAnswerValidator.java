package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;

import java.util.List;

public class TextAnswerValidator implements AnswerValidator {
    @Override
    public boolean isValid(final List<String> answers, final Quiz quiz, final List<QuizChoice> choices) {
        if (answers.size() != 1){
            return false;
        }

        final String answer = normalize(answers.get(0));
        final String expected = normalize(quiz.getAnswer());
        return answer.equalsIgnoreCase(expected);
    }

    protected String normalize(final String str){
        return str == null ? "" : str.replaceAll("(\\r\\n)", "\n");
    }
}
