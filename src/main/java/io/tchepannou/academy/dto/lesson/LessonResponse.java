package io.tchepannou.academy.dto.lesson;

import io.tchepannou.academy.dto.BaseResponse;

public class LessonResponse extends BaseResponse {
    private LessonDto lesson;

    public LessonResponse() {

    }
    public LessonResponse(final LessonDto lesson) {
        this.lesson = lesson;
    }

    public LessonDto getLesson() {
        return lesson;
    }

    public void setLesson(final LessonDto lesson) {
        this.lesson = lesson;
    }
}
