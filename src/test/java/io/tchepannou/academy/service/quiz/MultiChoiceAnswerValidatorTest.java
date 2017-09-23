package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiChoiceAnswerValidatorTest {
    final AnswerValidator validator = new MultiChoiceAnswerValidator();
    final Quiz quiz = new Quiz();
    final List<QuizChoice> choices = Arrays.asList(
            createQuizChoice(1, true),
            createQuizChoice(2, false),
            createQuizChoice(3, true)
    );

    @Test
    public void testIsValid() throws Exception {
        assertThat(validator.isValid(Collections.emptyList(), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("1", "2", "3"), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("1", "2", "3", "4"), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("1", "3", "999"), quiz, choices)).isFalse();

        assertThat(validator.isValid(Arrays.asList("1", "3"), quiz, choices)).isTrue();
        assertThat(validator.isValid(Arrays.asList("3", "1"), quiz, choices)).isTrue();
    }


    private QuizChoice createQuizChoice(Integer id, boolean answer){
        final QuizChoice choice = new QuizChoice();
        choice.setId(id);
        choice.setAnswer(answer);
        return choice;
    }

}
