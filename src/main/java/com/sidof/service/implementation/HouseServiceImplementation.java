package com.sidof.service.implementation;

import com.sidof.enume.HouseType;
import com.sidof.model.House;
import com.sidof.repo.HouseRepo;
import com.sidof.repo.HouseRepository;
import com.sidof.service.HouseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Boolean.TRUE;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class HouseServiceImplementation implements HouseService {
    private final HouseRepository houseRepository;
    private final HouseRepo houseRepos;
    /**
     * @param houseToSave
     * @return House.
     */
    @Override
    public House saveNewHouse(House houseToSave) {
        log.info("Saving new House: {}", houseToSave.getHouseType());
        return houseRepos.save(houseToSave);
    }

    /**
     * @param houseToUpdate
     * @return houseToUpdate.
     */
    @Override
    public House updateHouse(House houseToUpdate) {
        log.info("Updating House: {}", houseToUpdate.getHouseType());
        return houseRepos.save(houseToUpdate);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public House getHouse(Long id) {
        log.info("Fetching house by id: {}", id);
        return houseRepos.findById(id).get();
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean deleteHouse(Long id) {
        log.info("Deleting House by ID: {}", id);
        houseRepos.deleteById(id);
        return TRUE;
    }

    /**
     * @param houseType
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Page<House> getHouseByHouseType(HouseType houseType, int pageNumber, int pageSize) {
        log.info("Fetching houses with pagination" );
        return houseRepository.findByHouseType(houseType, PageRequest.of(pageNumber,pageSize));
    }

    /**
     * List of House without Pagination
     * @return  List<House>;
     */
    @Override
    public List<House> getHouses() {
        log.info("Fetching house without pagination");
        return houseRepos.findAll();
    }
}
