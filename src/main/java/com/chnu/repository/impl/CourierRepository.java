package com.chnu.repository.impl;

import com.chnu.model.Courier;
import com.chnu.repository.ICourierRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CourierRepository extends AbstractRepository<Courier, Long> implements ICourierRepository {
}
