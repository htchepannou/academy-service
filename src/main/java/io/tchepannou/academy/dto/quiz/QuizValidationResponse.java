package io.tchepannou.academy.dto.quiz;

import io.tchepannou.academy.dto.BaseResponse;

public class QuizValidationResponse extends BaseResponse{
    private boolean valid;
    private String message;

    public boolean isValid() {
        return valid;
    }

    public void setValid(final boolean valid) {
        this.valid = valid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
