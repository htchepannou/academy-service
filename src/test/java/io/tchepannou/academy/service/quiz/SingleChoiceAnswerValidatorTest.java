package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleChoiceAnswerValidatorTest {
    final AnswerValidator validator = new SingleChoiceAnswerValidator();
    final Quiz quiz = new Quiz();
    final List<QuizChoice> choices = Arrays.asList(
            createQuizChoice(1, true),
            createQuizChoice(2, false)
    );

    @Test
    public void testIsValid() throws Exception {
        assertThat(validator.isValid(Collections.emptyList(), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("1", "2"), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("2"), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("999"), quiz, choices)).isFalse();

        assertThat(validator.isValid(Arrays.asList("1"), quiz, choices)).isTrue();
    }


    private QuizChoice createQuizChoice(Integer id, boolean answer){
        final QuizChoice choice = new QuizChoice();
        choice.setId(id);
        choice.setAnswer(answer);
        return choice;
    }
}
