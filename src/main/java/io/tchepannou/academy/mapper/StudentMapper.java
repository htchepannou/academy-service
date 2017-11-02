package io.tchepannou.academy.mapper;

import io.tchepannou.academy.domain.Student;
import io.tchepannou.academy.domain.StudentSegment;
import io.tchepannou.academy.client.dto.StudentDto;
import io.tchepannou.academy.client.course.StudentSegmentDto;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentDto toStudentDto(final Student attendance){
        final StudentDto dto = new StudentDto();
        dto.setCourseId(attendance.getCourseId());
        dto.setId(attendance.getId());
        dto.setRoleId(attendance.getRoleId());
        dto.setCourseSegmentCount(attendance.getCourseSegmentCount());
        dto.setAttendedSegmentCount(attendance.getAttendedSegmentCount());
        return dto;
    }

    public StudentSegmentDto toStudentDto(final StudentSegment attendance){
        final StudentSegmentDto dto = new StudentSegmentDto();
        dto.setSegmentId(attendance.getSegmentId());
        return dto;
    }
}
