package io.tchepannou.academy.dto.student;

import io.tchepannou.academy.dto.BaseResponse;

public class StudentResponse extends BaseResponse{
    private StudentDto student;

    public StudentDto getStudent() {
        return student;
    }

    public void setStudent(final StudentDto student) {
        this.student = student;
    }
}
