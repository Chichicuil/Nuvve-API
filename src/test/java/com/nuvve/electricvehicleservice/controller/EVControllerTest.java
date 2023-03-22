package com.nuvve.electricvehicleservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuvve.electricvehicleservice.model.ElectricVehicle;
import com.nuvve.electricvehicleservice.repository.ElectricVehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EVControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ElectricVehicleRepository electricVehicleRepository;

    @InjectMocks
    private ElectricVehicleController electricVehicleController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(electricVehicleController).build();
    }

    @Test
    void testGetAllElectricVehicles() throws Exception {
        List<ElectricVehicle> electricVehicleList = new ArrayList<>();
        ElectricVehicle electricVehicle = new ElectricVehicle();
        electricVehicle.setMake("Tesla");
        electricVehicle.setModel("Model S");
        electricVehicle.setYear(2022);
        electricVehicleList.add(electricVehicle);

        when(electricVehicleRepository.findAll()).thenReturn(electricVehicleList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/electric-vehicles"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(new ObjectMapper().writeValueAsString(electricVehicleList)))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testGetElectricVehicleById() throws Exception {
        ElectricVehicle electricVehicle = new ElectricVehicle();
        electricVehicle.setId(1L);
        electricVehicle.setMake("Tesla");
        electricVehicle.setModel("Model S");
        electricVehicle.setYear(2022);

        when(electricVehicleRepository.findById(any(Long.class))).thenReturn(Optional.of(electricVehicle));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/electric-vehicles/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.make").value("Tesla"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Model S"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(2022))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testCreateElectricVehicle() throws Exception {
        ElectricVehicle electricVehicle = new ElectricVehicle();
        electricVehicle.setMake("Tesla");
        electricVehicle.setModel("Model S");
        electricVehicle.setYear(2022);

        when(electricVehicleRepository.save(any(ElectricVehicle.class))).thenReturn(electricVehicle);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/electric-vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(electricVehicle)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.make").value("Tesla"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Model S"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(2022))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testUpdateElectricVehicle() throws Exception {
        ElectricVehicle electricVehicle = new ElectricVehicle();
        electricVehicle.setId(1L);
        electricVehicle.setMake("Tesla");
        electricVehicle.setModel("Model S");
        electricVehicle.setYear(2022);

        when(electricVehicleRepository.findById(any(Long.class))).thenReturn(Optional.of(electricVehicle));
        when(electricVehicleRepository.save(any(ElectricVehicle.class))).thenReturn(electricVehicle);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/electric-vehicles/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(electricVehicle)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.make").value("Tesla"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.model").value("Model S"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.year").value(2022))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void testDeleteElectricVehicle() throws Exception {
        ElectricVehicle electricVehicle = new ElectricVehicle();
        electricVehicle.setId(1L);
        electricVehicle.setMake("Tesla");
        electricVehicle.setModel("Model S");
        electricVehicle.setYear(2022);

        when(electricVehicleRepository.findById(any(Long.class))).thenReturn(Optional.of(electricVehicle));

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/electric-vehicles/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
