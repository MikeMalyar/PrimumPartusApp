package com.chnu.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseRepository<T, PK extends Serializable> {

    Optional<T> findById(PK pk);

    T save(T object);

    T update(T object);

    void delete(T object);

    void deleteById(PK pk);

    List<T> findAll();

    List<T> findWithPagination(int first, int count);

    boolean existsById(PK pk);

}
