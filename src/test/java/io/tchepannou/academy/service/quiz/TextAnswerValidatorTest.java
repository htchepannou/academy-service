package io.tchepannou.academy.service.quiz;

import io.tchepannou.academy.domain.Quiz;
import io.tchepannou.academy.domain.QuizChoice;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TextAnswerValidatorTest {
    final AnswerValidator validator = new TextAnswerValidator();
    final List<QuizChoice> choices = Collections.emptyList();

    @Test
    public void isValidSimple() throws Exception {
        final Quiz quiz = createQuiz("Hello World");

        assertThat(validator.isValid(Collections.emptyList(), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("1"), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("Hello World", "2"), quiz, choices)).isFalse();

        assertThat(validator.isValid(Arrays.asList("Hello World"), quiz, choices)).isTrue();
        assertThat(validator.isValid(Arrays.asList("hello world"), quiz, choices)).isTrue();
    }

    @Test
    public void isValidTextWithCR() throws Exception {
        final Quiz quiz = createQuiz("Hello\nWorld");

        assertThat(validator.isValid(Collections.emptyList(), quiz, choices)).isFalse();
        assertThat(validator.isValid(Arrays.asList("Hello\nWorld"), quiz, choices)).isTrue();
        assertThat(validator.isValid(Arrays.asList("Hello\r\nWorld"), quiz, choices)).isTrue();
    }

    private Quiz createQuiz(String answer){
        final Quiz quiz = new Quiz();
        quiz.setAnswer(answer);
        return quiz;
    }
}
