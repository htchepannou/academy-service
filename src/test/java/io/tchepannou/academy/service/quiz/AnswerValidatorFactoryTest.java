package io.tchepannou.academy.service.quiz;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerValidatorFactoryTest {
    final AnswerValidatorFactory factory = new AnswerValidatorFactory();

    @Test
    public void getValidator() throws Exception {
        assertThat(factory.getValidator("singlechoice")).isInstanceOf(SingleChoiceAnswerValidator.class);
        assertThat(factory.getValidator("multichoice")).isInstanceOf(MultiChoiceAnswerValidator.class);
        assertThat(factory.getValidator("string")).isInstanceOf(TextAnswerValidator.class);
        assertThat(factory.getValidator("text")).isInstanceOf(LongTextAnswerValidator.class);
        assertThat(factory.getValidator("markdown")).isInstanceOf(LongTextAnswerValidator.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getValidatorUnknown() throws Exception {
        assertThat(factory.getValidator("????")).isInstanceOf(SingleChoiceAnswerValidator.class);
    }
}
