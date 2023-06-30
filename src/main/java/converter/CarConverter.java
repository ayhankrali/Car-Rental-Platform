package converter;

import dto.CarDTO;
import entity.Car;

public class CarConverter {
    public static Car fromDTO(CarDTO carDTO) {
        Car car = new Car();
        car.setBrand(carDTO.getBrand());
        car.setModel(carDTO.getModel());
        car.setSeatCount(carDTO.getSeats());
        car.setRentalPricePerDay(carDTO.getRentalPricePerDay());
        return car;
    }

    public static CarDTO toDTO(Car car) {
        CarDTO carDTO = new CarDTO();
        carDTO.setBrand(car.getBrand());
        carDTO.setModel(car.getModel());
        carDTO.setSeats(car.getSeatCount());
        carDTO.setRentalPricePerDay(car.getRentalPricePerDay());
        return carDTO;
    }
}
