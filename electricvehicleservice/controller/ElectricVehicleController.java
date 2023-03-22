package com.nuvve.electricvehicleservice.controller;

import com.nuvve.electricvehicleservice.exception.ResourceNotFoundException;
import com.nuvve.electricvehicleservice.model.ElectricVehicle;
import com.nuvve.electricvehicleservice.repository.ElectricVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@Validated
public class ElectricVehicleController {

    @Autowired
    private ElectricVehicleRepository electricVehicleRepository;

    // Get all electric vehicles
    @GetMapping("/electric-vehicles")
    public List<ElectricVehicle> getAllElectricVehicles() {
        return electricVehicleRepository.findAll();
    }

    // Get a single electric vehicle
    @GetMapping("/electric-vehicles/{id}")
    public ResponseEntity<ElectricVehicle> getElectricVehicleById(@PathVariable(value = "id") Long electricVehicleId)
            throws ResourceNotFoundException {
        ElectricVehicle electricVehicle = electricVehicleRepository.findById(electricVehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Electric Vehicle not found for this id :: " + electricVehicleId));
        return ResponseEntity.ok().body(electricVehicle);
    }

    // Create an electric vehicle
    @PostMapping("/electric-vehicles")
    public ElectricVehicle createElectricVehicle(@Valid @RequestBody ElectricVehicle electricVehicle) {
        return electricVehicleRepository.save(electricVehicle);
    }

    // Update an electric vehicle
    @PutMapping("/electric-vehicles/{id}")
    public ResponseEntity<ElectricVehicle> updateElectricVehicle(@PathVariable(value = "id") Long electricVehicleId,
                                                                 @Valid @RequestBody ElectricVehicle electricVehicleDetails)
            throws ResourceNotFoundException {
        ElectricVehicle electricVehicle = electricVehicleRepository.findById(electricVehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Electric Vehicle not found for this id :: " + electricVehicleId));

        electricVehicle.setMake(electricVehicleDetails.getMake());
        electricVehicle.setModel(electricVehicleDetails.getModel());
        electricVehicle.setYear(electricVehicleDetails.getYear());
        final ElectricVehicle updatedElectricVehicle = electricVehicleRepository.save(electricVehicle);
        return ResponseEntity.ok(updatedElectricVehicle);
    }

    // Delete an electric vehicle
    @DeleteMapping("/electric-vehicles/{id}")
    public Map<String, Boolean> deleteElectricVehicle(@PathVariable(value = "id") Long electricVehicleId)
            throws ResourceNotFoundException {
        ElectricVehicle electricVehicle = electricVehicleRepository.findById(electricVehicleId)
                .orElseThrow(() -> new ResourceNotFoundException("Electric Vehicle not found for this id :: " + electricVehicleId));

        electricVehicleRepository.delete(electricVehicle);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
