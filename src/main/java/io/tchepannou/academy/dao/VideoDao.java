package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.Video;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoDao extends CrudRepository<Video, Integer> {
}
