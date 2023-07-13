package tn.eesprit.gestionevenementback.Services;

import tn.eesprit.gestionevenementback.Entities.Reservation;

import java.io.IOException;
import java.util.List;

public interface IReservationService {
    List<Reservation> retrieveAllReservations();
    Reservation addOrUpdateReservation(Reservation Reservation);
    Reservation updateReservation(Long eventId, Reservation Reservation);
    Reservation UpdateStatusReservation(Reservation reservation);
    Reservation UpdateSeated(Integer id,Reservation Reservation) throws IOException;
    Reservation retrieveReservation(Integer id);
    void removeReservation(Integer id);
}
