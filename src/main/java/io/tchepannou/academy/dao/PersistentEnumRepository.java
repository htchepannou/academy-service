package io.tchepannou.academy.dao;

import io.tchepannou.academy.domain.PersistentEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PersistentEnumRepository<T extends PersistentEnum> extends CrudRepository<T, Integer> {
    T findByNameIgnoreCase (String name);
    T findByRank (Integer rank);
}
