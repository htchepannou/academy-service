package io.tchepannou.academy.dto.attendance;

import io.tchepannou.academy.dto.BaseResponse;

public class AttendanceResponse extends BaseResponse{
    private CourseAttendanceDto attendance;

    public CourseAttendanceDto getAttendance() {
        return attendance;
    }

    public void setAttendance(final CourseAttendanceDto attendance) {
        this.attendance = attendance;
    }
}
