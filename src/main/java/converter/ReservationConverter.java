package converter;

import dto.ReservationDTO;
import entity.Reservation;
import jakarta.persistence.Converter;

import java.util.List;
@Converter
public class ReservationConverter {
    public static Reservation fromDTO(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setStartDate(reservationDTO.getStartDate());
        reservation.setEndDate(reservationDTO.getEndDate());
        reservation.setTotalPrice(reservationDTO.getTotalPrice());
        reservation.setUser(UserConverter.fromDTO(reservationDTO.getUser()));
        reservation.setCar(CarConverter.fromDTO(reservationDTO.getCar()));
        return reservation;
    }

    public static ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setStartDate(reservation.getStartDate());
        reservationDTO.setEndDate(reservation.getEndDate());
        reservationDTO.setTotalPrice(reservation.getTotalPrice());
        reservationDTO.setUser(UserConverter.toDTO(reservation.getUser()));
        reservationDTO.setCar(CarConverter.toDTO(reservation.getCar()));
        return reservationDTO;
    }

    public static List<ReservationDTO> toDTOList(List<Reservation> reservations) {
        return null;
    }
}
