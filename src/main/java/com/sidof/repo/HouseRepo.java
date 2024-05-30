package com.sidof.repo;

import com.sidof.model.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepo extends JpaRepository<House,Long> {
}
