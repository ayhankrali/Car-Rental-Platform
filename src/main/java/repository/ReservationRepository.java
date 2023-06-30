package repository;

import entity.Car;
import entity.Reservation;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByUser(User user);
    List<Reservation> findByCar(Car car);
    List<Reservation> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
