package dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReservationDTO {
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalPrice;
    private UserDTO user;
    private CarDTO car;

}
