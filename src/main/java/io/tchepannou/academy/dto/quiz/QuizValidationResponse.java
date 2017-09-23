package io.tchepannou.academy.dto.quiz;

import io.tchepannou.academy.dto.BaseResponse;

public class QuizValidationResponse extends BaseResponse{
    boolean valid;

    public boolean isValid() {
        return valid;
    }

    public void setValid(final boolean valid) {
        this.valid = valid;
    }
}
