package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDTO {
    private String brand;
    private String model;
    private int seats;
    private double rentalPricePerDay;

}
