package io.tchepannou.academy.mapper;

import io.tchepannou.academy.domain.CourseAttendance;
import io.tchepannou.academy.domain.SegmentAttendance;
import io.tchepannou.academy.dto.attendance.CourseAttendanceDto;
import io.tchepannou.academy.dto.attendance.SegmentAttendanceDto;
import org.springframework.stereotype.Component;

@Component
public class AttendanceMapper {
    public CourseAttendanceDto toCourseAttendanceDto(final CourseAttendance attendance){
        final CourseAttendanceDto dto = new CourseAttendanceDto();
        dto.setCourseId(attendance.getCourseId());
        dto.setCurrentSegmentId(attendance.getCurrentSegmentId());
        dto.setId(attendance.getId());
        dto.setAttendanceDateTime(attendance.getAttendanceDateTime());
        dto.setStudentId(attendance.getStudentId());
        return dto;
    }

    public SegmentAttendanceDto toSegmentAttendanceDto(final SegmentAttendance attendance){
        final SegmentAttendanceDto dto = new SegmentAttendanceDto();
        dto.setSegmentId(attendance.getSegmentId());
        dto.setAttendanceDateTime(attendance.getAttendanceDateTime());
        dto.setId(attendance.getId());
        return dto;

    }
}
