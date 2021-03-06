package io.tchepannou.academy.exception;

public enum BusinessError {
    BAD_REQUEST("BAD_REQUEST", "Request is invalid"),
    BAD_COURSE_LEVEL("BAD_COURSE_LEVEL", "Level is invalid"),
    BAD_COURSE_STATUS("BAD_COURSE_STATUS", "Status is invalid"),
    BAD_VIDEO_URL("BAD_VIDEO_URL", "The URL of the video is not supported"),
    STUDENT_NOT_FOUND("STUDENT_NOT_FOUND", "Attendance Not found"),
    COURSE_NOT_FOUND("COURSE_NOT_FOUND", "Course Not found"),
    QUIZ_NOT_FOUND("QUIZ_NOT_FOUND", "Quiz Not found"),
    LESSON_NOT_FOUND("LESSON_NOT_FOUND", "Leg Not found"),
    SEGMENT_NOT_FOUND("SEGMENT_NOT_FOUND", "Segment not found"),
    VIDEO_NOT_FOUND("VIDEO_NOT_FOUND", "Video Not found"),
    ;

    private String code;
    private String description;

    BusinessError(String code, String description){
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
