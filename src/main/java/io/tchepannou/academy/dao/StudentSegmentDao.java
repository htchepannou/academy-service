package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.StudentSegment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentSegmentDao extends CrudRepository<StudentSegment, Integer>{
    StudentSegment findByStudentIdAndSegmentId(Integer studentId, Integer segmentId);
    List<StudentSegment> findByStudentId(Integer studentId);
}
