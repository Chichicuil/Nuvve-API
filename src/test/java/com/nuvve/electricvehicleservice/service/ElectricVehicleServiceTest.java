package com.nuvve.electricvehicleservice.service;

import com.nuvve.electricvehicleservice.model.ElectricVehicle;
import com.nuvve.electricvehicleservice.repository.ElectricVehicleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ElectricVehicleServiceTest {

    @Mock
    private ElectricVehicleRepository electricVehicleRepository;

    @InjectMocks
    private ElectricVehicleService electricVehicleService;

    @Test
    public void testCreate() {
        ElectricVehicle ev = new ElectricVehicle();
        ev.setId(1L);
        ev.setMake("Tesla");
        ev.setModel("Model S");
        ev.setYear(2022);

        when(electricVehicleRepository.save(any(ElectricVehicle.class))).thenReturn(ev);

        ElectricVehicle result = electricVehicleService.create(ev);

        assertNotNull(result);
        assertEquals(result.getId(), ev.getId());
        assertEquals(result.getMake(), ev.getMake());
        assertEquals(result.getModel(), ev.getModel());
        assertEquals(result.getYear(), ev.getYear());

        verify(electricVehicleRepository, times(1)).save(any(ElectricVehicle.class));
    }

    @Test
    public void testRead() {
        ElectricVehicle ev = new ElectricVehicle();
        ev.setId(1L);
        ev.setMake("Tesla");
        ev.setModel("Model S");
        ev.setYear(2022);

        when(electricVehicleRepository.findById(anyLong())).thenReturn(Optional.of(ev));

        ElectricVehicle result = electricVehicleService.read(1L);

        assertNotNull(result);
        assertEquals(result.getId(), ev.getId());
        assertEquals(result.getMake(), ev.getMake());
        assertEquals(result.getModel(), ev.getModel());
        assertEquals(result.getYear(), ev.getYear());

        verify(electricVehicleRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testUpdate() {
        ElectricVehicle ev = new ElectricVehicle();
        ev.setId(1L);
        ev.setMake("Tesla");
        ev.setModel("Model S");
        ev.setYear(2022);

        when(electricVehicleRepository.save(any(ElectricVehicle.class))).thenReturn(ev);

        ElectricVehicle result = electricVehicleService.update(1L, ev);

        assertNotNull(result);
        assertEquals(result.getId(), ev.getId());
        assertEquals(result.getMake(), ev.getMake());
        assertEquals(result.getModel(), ev.getModel());
        assertEquals(result.getYear(), ev.getYear());

        verify(electricVehicleRepository, times(1)).save(any(ElectricVehicle.class));
    }

    @Test
    public void testDelete() {
        doNothing().when(electricVehicleRepository).deleteById(anyLong());

        electricVehicleService.delete(1L);

        verify(electricVehicleRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void testGetAll() {
        ElectricVehicle ev1 = new ElectricVehicle();
        ev1.setId(1L);
        ev1.setMake("Tesla");
        ev1.setModel("Model S");
        ev1.setYear(2022);

        ElectricVehicle ev2 = new ElectricVehicle();
        ev2.setId(2L);
        ev2.setMake("Ford");
        ev2.setModel("Mustang Mach-E");
        ev2.setYear(2022);

        List<ElectricVehicle> evList = Arrays.asList(ev1, ev2);

        when(electricVehicleRepository.findAll()).thenReturn(evList);

        List<ElectricVehicle> result = electricVehicleService.getAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(result.get(0).getId(), ev1.getId());
        assertEquals(result.get(0).getMake(), ev1.getMake());
        assertEquals(result.get(0).getModel(), ev1.getModel());
        assertEquals(result.get(0).getYear(), ev1.getYear());
        assertEquals(result.get(1).getId(), ev2.getId());
        assertEquals(result.get(1).getMake(), ev2.getMake());
        assertEquals(result.get(1).getModel(), ev2.getModel());
        assertEquals(result.get(1).getYear(), ev2.getYear());

        verify(electricVehicleRepository, times(1)).findAll();
    }
}

