package com.sidof.service;
/*
 *   Author: sidof
 *   Since : 28/05/2024
 */

import com.sidof.enume.HouseType;
import com.sidof.model.House;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HouseService {
    House saveNewHouse(House houseToSave);
    House updateHouse(House houseToUpdate);
    House getHouse(Long id);
    Boolean deleteHouse(Long id);
    Page<House> getHouseByHouseType(HouseType houseType, int pageNumber, int pageSize);
    List<House> getHouses();
}
