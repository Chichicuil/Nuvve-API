package com.nuvve.electricvehicleservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nuvve.electricvehicleservice.model.ElectricVehicle;

@Repository
public interface ElectricVehicleRepository extends JpaRepository<ElectricVehicle, Long> {

}
