package com.chnu.repository.impl;

import com.chnu.model.Transport;
import com.chnu.repository.ITransportRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TransportRepository extends AbstractRepository<Transport, Long> implements ITransportRepository {
}
