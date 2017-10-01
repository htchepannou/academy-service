package io.tchepannou.academy.service.quiz;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MarkdownAnswerValidatorTest {
    final MarkdownAnswerValidator validator = new MarkdownAnswerValidator();

    @Test
    public void normalizeShouldRemoveEmptyLines() throws Exception {
        final String text = "A\n\nB\r\nC";
        assertThat(validator.normalize(text)).isEqualTo("A\nB\nC");
    }

    @Test
    public void normalizeShouldRightTrimLines() throws Exception {
        final String text = "A \n\n B   \r\nC";
        assertThat(validator.normalize(text)).isEqualTo("A\n B\nC");
    }
}
