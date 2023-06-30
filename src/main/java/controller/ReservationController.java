package controller;

import converter.ReservationConverter;
import dto.CarDTO;
import dto.ReservationDTO;
import entity.Car;
import entity.Reservation;
import entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ReservationService;
import service.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {
    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        Reservation reservation = reservationService.getReservationById(id);
        if (reservation != null) {
            ReservationDTO reservationDTO = ReservationConverter.toDTO(reservation);
            return ResponseEntity.ok(reservationDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/user")
    public ResponseEntity<List<ReservationDTO>> getReservationsByUser(@RequestParam("userId") Long userId) {
        User user = UserService.getUserById(userId);
        if (user != null) {
            List<Reservation> reservations = reservationService.getReservationsByUser(user);
            List<ReservationDTO> reservationDTOs = ReservationConverter.toDTOList(reservations);
            return ResponseEntity.ok(reservationDTOs);
        } else {
            return ResponseEntity.notFound().build();

        }
    }


    @GetMapping("/car")
    public ResponseEntity<List<ReservationDTO>> getReservationsByCar(@RequestParam("carId") Long carId) {
        CarController carService = null;
        ResponseEntity<CarDTO> car = carService.getCarById(carId);
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/period")
    public ResponseEntity<List<ReservationDTO>> getReservationsByPeriod(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        List<Reservation> reservations = reservationService.getReservationsByPeriod(start, end);
        List<ReservationDTO> reservationDTOs = ReservationConverter.toDTOList(reservations);
        return ResponseEntity.ok(reservationDTOs);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createReservation(@RequestBody ReservationDTO reservationDTO) {
        Reservation reservation = ReservationConverter.fromDTO(reservationDTO);
        reservationService.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        Reservation existingReservation = reservationService.getReservationById(id);
        if (existingReservation != null) {
            reservationService.deleteReservation(existingReservation);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}