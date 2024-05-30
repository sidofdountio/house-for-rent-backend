package com.sidof.repo;

import com.sidof.model.House;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static com.sidof.enume.HouseType.APARTMENT;
import static com.sidof.enume.HouseType.ROOM;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.springframework.data.domain.PageRequest.*;
@Slf4j
@DataJpaTest
class HouseRepositoryTest {

    @Autowired
    private HouseRepository underTestPagination;
    @Autowired
    private HouseRepo underTest;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    /**
     *  This Test check pagination list.
     */
    @Test
    void findByHouseType() {
//        Given
        House house = new House(1L, ROOM,"Trois lamapadaire","yaounde simbock",1,1,15000.00);
        House house1 = new House(2L, APARTMENT,"Trois lamapadaire","yaounde simbock",3,2,80000.00);
        House house2 = new House(3L, APARTMENT,"Trois lamapadaire","yaounde simbock",4,2,80000.00);
//        When
        underTest.save(house);
        underTest.save(house1);
        underTest.save(house2);
//        Then
        int pageNumber =0;
        int pageSize= 10;
        Page<House> byHouseType= underTestPagination.findByHouseType(APARTMENT, of(pageNumber, pageSize));
        List<House> houses = byHouseType.getContent();
        log.info("Test fecthing houses {}", houses);
        assert(byHouseType).hasContent();
        assertFalse(byHouseType.isEmpty());
//        verify(underTestPagination.findByNameContaining(name, of(pageNumber,pageSize)));

    }
}