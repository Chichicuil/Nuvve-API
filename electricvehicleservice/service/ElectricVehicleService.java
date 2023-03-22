package com.nuvve.electricvehicleservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nuvve.electricvehicleservice.model.ElectricVehicle;
import com.nuvve.electricvehicleservice.repository.ElectricVehicleRepository;

@Service
public class ElectricVehicleService {

    @Autowired
    private ElectricVehicleRepository repository;

    public ElectricVehicle create(ElectricVehicle ev) {
        return repository.save(ev);
    }

    public ElectricVehicle read(Long id) {
        return repository.findById(id).orElse(null);
    }

    public ElectricVehicle update(Long id, ElectricVehicle ev) {
        ev.setId(id);
        return repository.save(ev);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<ElectricVehicle> getAll() {
        return repository.findAll();
    }
}
