package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StringAnswerValidatorTest {
    final AnswerValidator validator = new StringAnswerValidator();
    final Quiz quiz = createQuiz("ToTO");
    final List<QuizChoice> choices = Collections.emptyList();

    @Test
    public void isValid() throws Exception {
        assertThat(validator.isValid(Collections.emptyList(), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("1"), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("ToTO", "2"), quiz, choices)).isFalse();

        assertThat(validator.isValid(Arrays.asList("ToTO"), quiz, choices)).isTrue();
        assertThat(validator.isValid(Arrays.asList("toto"), quiz, choices)).isTrue();
    }

    private Quiz createQuiz(String answer){
        final Quiz quiz = new Quiz();
        quiz.setAnswer(answer);
        return quiz;
    }
}
