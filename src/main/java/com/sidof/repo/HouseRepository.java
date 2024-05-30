package com.sidof.repo;

import com.sidof.enume.HouseType;
import com.sidof.model.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * This repository will be used only for pagination.
 * **/
public interface HouseRepository extends PagingAndSortingRepository<House,Long> {
    Page<House> findByHouseType(HouseType housetype, Pageable pageable);
}
