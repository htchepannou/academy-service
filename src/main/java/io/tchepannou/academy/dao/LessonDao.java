package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonDao extends CrudRepository<Lesson, Integer>{
    List<Lesson> findByCourseId(Integer courseId, Pageable pageable);
}
