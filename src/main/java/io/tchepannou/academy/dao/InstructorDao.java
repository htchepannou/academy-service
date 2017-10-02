package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.Instructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorDao extends CrudRepository<Instructor, Integer>{
    List<Instructor> findByCourseId(Integer courseId);
}
