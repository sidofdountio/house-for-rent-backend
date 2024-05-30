package com.sidof.service.implementation;

import com.sidof.model.House;
import com.sidof.repo.HouseRepo;
import com.sidof.repo.HouseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.Optional;

import static com.sidof.enume.HouseType.APARTMENT;
import static com.sidof.enume.HouseType.ROOM;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.data.domain.PageRequest.of;

/**
 * Junit Test avery single method on this  class.
 */
@ExtendWith(MockitoExtension.class)
class HouseServiceImplementationTest {
    @Autowired
    private HouseServiceImplementation underTest;
    @Mock
    private HouseRepository houseRepository;
    @Mock
    private HouseRepo houseRepos;

    //    Instantiated service class by passing constructor parameter.
    @BeforeEach
    void setUp() {
        underTest = new HouseServiceImplementation(houseRepository, houseRepos);
    }

    @AfterEach
    void tearDown() {
        houseRepos.deleteAll();
    }

    @Test
    void saveNewHouse() {
//        Given
        House house = new House(1L, ROOM, "Trois lamapadaire", "yaounde simbock", 1, 1, 15000.00);
        underTest.saveNewHouse(house);
//        When
        ArgumentCaptor<House> houseArgumentCaptor = ArgumentCaptor.forClass(House.class);
        verify(houseRepos).save(houseArgumentCaptor.capture());
        House houseArgumentCaptorValue = houseArgumentCaptor.getValue();
//        Then
        assertEquals(house, houseArgumentCaptorValue);
    }

    @Test
    void updateHouse() {
//        Given
        House house1 = new House(2L, APARTMENT, "Trois lamapadaire", "yaounde simbock", 3, 2, 80000.00);
        underTest.saveNewHouse(house1);
//        House house = houseRepos.findById(2L).get();
//        When
        when(houseRepos.save(house1)).thenReturn(house1);
        house1.setAddress("Grande chefferie");
        House updateHouse = underTest.updateHouse(house1);
//        Then
        assertEquals(updateHouse.getAddress(), "Grande chefferie");
        System.out.println(updateHouse);
    }

    @Test
    @DisplayName("Get house by id")
    void CanGetHouseById() {
//        Given
        House house = new House(1L, ROOM, "Trois lamapadaire", "yaounde simbock", 1, 1, 15000.00);
        underTest.saveNewHouse(house);
//        When
        when(houseRepos.findById(house.getId())).thenReturn(Optional.of(house));
        House houseById = underTest.getHouse(house.getId());
//        Then
        verify(houseRepos).findById(house.getId());
        assertEquals(house, houseById);
    }

    @Test
    void deleteHouse() {
//         Given
        House house = new House(1L, ROOM, "Trois lamapadaire", "yaounde simbock", 1, 1, 15000.00);
        underTest.saveNewHouse(house);
//        When
        Boolean deleteHouse = underTest.deleteHouse(house.getId());
//        Then
//        House houseById = underTest.getHouse(house.getId());
        verify(houseRepos).deleteById(house.getId());
        assertTrue(deleteHouse);
//        assertNull(houseById);
    }

    @Test
    void getHouseByHouseType() {
        //        Given
        House house = new House(1L, ROOM, "Trois lamapadaire", "yaounde simbock", 1, 1, 15000.00);
        House house1 = new House(2L, APARTMENT, "Trois lamapadaire", "yaounde simbock", 3, 2, 80000.00);
        House house2 = new House(3L, APARTMENT, "Trois lamapadaire", "yaounde simbock", 4, 2, 80000.00);
        underTest.saveNewHouse(house);
        underTest.saveNewHouse(house1);
        underTest.saveNewHouse(house2);
        Page<House> housePage = new PageImpl<>(List.of(house1, house2));
        int pageNumber = 0;
        int pageSize = 10;
        //        When
        when(houseRepository.findByHouseType(APARTMENT, of(pageNumber, pageSize))).thenReturn(housePage);
        Page<House> houseByHouseType = underTest.getHouseByHouseType(APARTMENT, pageNumber, pageSize);
        System.out.println(houseByHouseType.getContent());
//        Then
        assertNotNull(houseByHouseType.getContent(), "List ist not empty");
        assertFalse(houseByHouseType.isEmpty(), "List ist not empty");
        verify(houseRepository).findByHouseType(APARTMENT, of(pageNumber, pageSize));
    }
}