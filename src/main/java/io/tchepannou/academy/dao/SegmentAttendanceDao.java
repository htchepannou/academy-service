package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.SegmentAttendance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentAttendanceDao extends CrudRepository<SegmentAttendance, Integer>{
    SegmentAttendance findByCourseAttendanceIdAndSegmentId(Integer courseAttendanceId, Integer SegmentId);
    List<SegmentAttendance> findByCourseAttendanceId(Integer courseAttendanceId);
}
