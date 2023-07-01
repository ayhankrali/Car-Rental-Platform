package repository;

import entity.Car;
import entity.Reservation;
import entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    @Query("SELECT r FROM Reservation r WHERE r.user.id = :user")
    List<Reservation> findByUser(User user);
    @Query("SELECT r FROM Reservation r WHERE r.car.id = :car")
    List<Reservation> findByCar(Car car);
    @Query("SELECT r FROM Reservation r WHERE r.startDate >= :startDate AND r.endDate <= :endDate")
    List<Reservation> findByStartDateBetween(LocalDate startDate, LocalDate endDate);
}
