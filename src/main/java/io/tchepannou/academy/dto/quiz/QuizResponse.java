package io.tchepannou.academy.dto.quiz;

import io.tchepannou.academy.dto.BaseResponse;

public class QuizResponse extends BaseResponse{
    private QuizDto quiz;

    public QuizDto getQuiz() {
        return quiz;
    }

    public void setQuiz(final QuizDto quiz) {
        this.quiz = quiz;
    }
}
