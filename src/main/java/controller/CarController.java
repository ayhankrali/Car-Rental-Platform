package controller;

import converter.CarConverter;
import dto.CarDTO;
import entity.Car;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.CarService;

@RestController
@RequestMapping("/cars")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        Car car = carService.getCarById(id);
        if (car != null) {
            CarDTO carDTO = CarConverter.toDTO(car);
            return ResponseEntity.ok(carDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addCar(@RequestBody CarDTO carDTO) {
        Car car = CarConverter.fromDTO(carDTO);
        carService.addCar(car);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        Car existingCar = carService.getCarById(id);
        if (existingCar != null) {
            Car updatedCar = CarConverter.fromDTO(carDTO);
            updatedCar.setId(id);
            carService.updateCar(updatedCar);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


}


