package io.tchepannou.academy.mapper;

import io.tchepannou.academy.domain.Instructor;
import io.tchepannou.academy.dto.instructor.InstructorDto;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {
    public InstructorDto toInstructorDto(Instructor instr){
        final InstructorDto dto = new InstructorDto();
        dto.setRoleId(instr.getRoleId());
        return dto;
    }
}
