package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.VideoType;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoTypeDao extends PersistentEnumRepository<VideoType> {
}
