package service;

import entity.Car;
import entity.Reservation;
import entity.User;
import org.springframework.stereotype.Service;
import repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;
    private CarService carService;
    private UserService userService;

    public ReservationService(ReservationRepository reservationRepository, CarService carService, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.carService = carService;
        this.userService = userService;
    }

    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    public List<Reservation> getReservationsByUser(User user) {
        return reservationRepository.findByUser(user);
    }

    public List<Reservation> getReservationsByCar(Car car) {
        return reservationRepository.findByCar(car);
    }

    public List<Reservation> getReservationsByPeriod(LocalDate startDate, LocalDate endDate) {
        return reservationRepository.findByStartDateBetween(startDate, endDate);
    }

    public void createReservation(Reservation reservation) {
        // Calculate reservation price based on the rental price per day and duration
        double rentalPricePerDay = reservation.getCar().getRentalPricePerDay();
        int duration = (int) (reservation.getEndDate().toEpochDay() - reservation.getStartDate().toEpochDay()) + 1;
        double totalPrice = rentalPricePerDay * duration;
        reservation.setTotalPrice(totalPrice);

        reservationRepository.save(reservation);
    }

    public void deleteReservation(Reservation reservation) {
        reservationRepository.delete(reservation);
    }
}
