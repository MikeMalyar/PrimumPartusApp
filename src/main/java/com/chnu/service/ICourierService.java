package com.chnu.service;

import com.chnu.model.Courier;

import java.util.List;
import java.util.Optional;

public interface ICourierService {

    Optional<Courier> save(Courier courier);

    Optional<Courier> findById(Long pk);

    Courier update(Courier object);

    void delete(Courier object);

    boolean existsById(Long pk);

    void deleteById(Long pk);

    List<Courier> findAll();

    List<Courier> findWithPagination(int first, int count);
}
