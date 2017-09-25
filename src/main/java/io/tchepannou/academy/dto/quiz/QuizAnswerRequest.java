package io.tchepannou.academy.dto.quiz;

import javax.validation.constraints.NotNull;
import java.util.List;

public class QuizAnswerRequest {
    @NotNull
    public List<String> values;

    public List<String> getValues() {
        return values;
    }

    public void setValues(final List<String> values) {
        this.values = values;
    }
}
