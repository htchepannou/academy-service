package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.Segment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SegmentDao extends CrudRepository<Segment, Integer>{
    List<Segment> findByLessonId(Integer lessonId, Pageable pageable);
    List<Segment> findByCourseId(Integer courseId, Pageable pageable);
}
