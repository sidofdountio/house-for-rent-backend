package com.sidof.api;

import com.sidof.config.Response;
import com.sidof.enume.HouseType;
import com.sidof.model.House;
import com.sidof.service.implementation.HouseServiceImplementation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.sidof.enume.HouseType.APARTMENT;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Author       : sidof <br>
 * LinkedIn    :  <a href="https://www.linkedin.com/in/sidof-dountio/">sidofDountio</a> <br>
 * Since       : 29/05/2024  <br>
 * Version    : v1.0.0 <br>
 * House controller <br>
 * Url : <a href='http://localhost:8084/ap/v1/house-for-rent'>http://localhost:8084/ap/v1/house-for-rent</a>
 */
@RestController
@RequestMapping("/ap/v1/house-for-rent")
@CrossOrigin(origins = "*", maxAge = 3600, allowedHeaders = "*")
@RequiredArgsConstructor
public class HouseApi {
    private final HouseServiceImplementation houseService;

    @GetMapping("/{houseType}/{pageNumber}/{pageSize}")
    public ResponseEntity<Response> getHouseByType(
            @PathVariable("houseType") HouseType houseType,
            @PathVariable("pageNumber") int pageNumber,
            @PathVariable("pageSize") int pageSize
    ) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("houses", houseService.getHouseByHouseType(houseType,pageNumber,pageSize)))
                        .message("house retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/page")
    public ResponseEntity<Response> getHouseByTypeWithParam(
            @RequestParam Optional<HouseType>  houseType,
            @RequestParam Optional<Integer> pageNumber,
            @RequestParam Optional<Integer> pageSize
    ) throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("houses", houseService.getHouseByHouseType(houseType.orElse(APARTMENT),pageNumber.orElse(0),pageSize.orElse(10))))
                        .message("house retrieved by house type")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping
    public ResponseEntity<Response> getHouses() throws InterruptedException {
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("houses", houseService.getHouses()))
                        .message("house retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<Response> saveHouse(@RequestBody House house){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("houses", houseService.saveNewHouse(house)))
                        .message("house created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<Response> updateHouse(@RequestBody House house){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("houses", houseService.updateHouse(house)))
                        .message("house updated")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getHouse(@PathVariable("id")Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("houses", houseService.getHouse(id)))
                        .message("house retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteHouse(@PathVariable("id")Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("houses", houseService.deleteHouse(id)))
                        .message("house deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
