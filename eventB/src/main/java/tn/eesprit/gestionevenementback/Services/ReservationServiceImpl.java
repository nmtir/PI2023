package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Entities.Reservation;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Repository.EventRepository;
import tn.eesprit.gestionevenementback.Repository.ReservationRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
 public class ReservationServiceImpl implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Reservation> retrieveAllReservations(){return reservationRepository.findAll();}

    @Override
    public Reservation addOrUpdateReservation(Reservation Reservation) {
        return null;
    }

    @Override
    public Reservation retrieveReservation(Integer id) {
        return null;
    }

    @Override
    public Reservation affecteReservatioToUser(Long eventId,Long userID){

        Optional<User> user=userRepository.findById(userID);

        User _user=user.get();



        Optional<Event> event=eventRepository.findById(eventId);

        Event _event=event.get();
        _user.getEvents().add(_event);
        userRepository.save(_user);

        Reservation reservation=new Reservation(_user,_event);

        return reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }



}
