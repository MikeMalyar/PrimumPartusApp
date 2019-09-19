package com.chnu.service.impl;

import com.chnu.model.Courier;
import com.chnu.repository.ICourierRepository;
import com.chnu.repository.ITransportRepository;
import com.chnu.service.ICourierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourierService implements ICourierService {

    private final ICourierRepository courierRepository;
    private final ITransportRepository transportRepository;

    @Autowired
    public CourierService(ICourierRepository courierRepository, ITransportRepository transportRepository){
        this.courierRepository = courierRepository;
        this.transportRepository = transportRepository;
    }

    @Override
    public Optional<Courier> save(Courier courier) {
        if (!findById(courier.getUserId()).isPresent()) return Optional.ofNullable(courierRepository.save(courier));
        return Optional.empty();
    }

    public Optional<Courier> findById(Long id){
        return courierRepository.findById(id);
    }

    public Courier update(Courier object){
        return courierRepository.update(object);
    }

    public boolean existsById(Long pk){
        return courierRepository.existsById(pk);
    }

    public void deleteById(Long pk){
        courierRepository.deleteById(pk);
    }

    public void delete(Courier entity){
        courierRepository.delete(entity);
    }

    public List<Courier> findWithPagination(int first, int count){
        return courierRepository.findWithPagination(first, count);
    }

    public List<Courier> findAll(){
        return courierRepository.findAll();
    }
}
