package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.Leg;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegDao extends CrudRepository<Leg, Integer>{
    List<Leg> findByCourseId(Integer courseId, Pageable pageable);
}
