package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Activity;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Entities.Reservation;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Repository.ActivityRepository;
import tn.eesprit.gestionevenementback.Repository.EventRepository;
import tn.eesprit.gestionevenementback.Repository.ReservationRepository;
import tn.eesprit.gestionevenementback.Repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
 public class ReservationServiceImpl implements IReservationService{
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ActivityRepository activityRepository;

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
    public Reservation affecteReservatioToUser(Long eventId,Long userID,List<Long> activites){
        System.out.println(eventId);
        System.out.println(userID);
        System.out.println(activites);

        Optional<User> user=userRepository.findById(userID);

        User _user=user.get();



        Optional<Event> event=eventRepository.findById(eventId);
        Set<Activity> activitySet=new HashSet<>();
        Event _event=event.get();
        System.out.println(eventId);
        _user.getEvents().add(_event);
       userRepository.save(_user);

       activites.forEach(aLong -> {
        activitySet.add(activityRepository.findByActivityId(aLong).get());
       });

        Reservation reservation=new Reservation(_user,_event,activitySet);

        return reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }



}
