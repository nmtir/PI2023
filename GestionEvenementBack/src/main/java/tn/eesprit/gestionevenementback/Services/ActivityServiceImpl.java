package tn.eesprit.gestionevenementback.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.eesprit.gestionevenementback.Entities.Activity;
import tn.eesprit.gestionevenementback.Entities.Event;
import tn.eesprit.gestionevenementback.Entities.Facture;
import tn.eesprit.gestionevenementback.Entities.User;
import tn.eesprit.gestionevenementback.Exception.ResourceNotFoundException;
import tn.eesprit.gestionevenementback.Repository.ActivityRepository;
import tn.eesprit.gestionevenementback.Repository.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Service
 public class ActivityServiceImpl implements IActivityService{

@Autowired
    EventRepository eventRepository;
@Autowired
    ActivityRepository activityRepository;
    @Override
    public Activity affecteActivityToEvent(Activity activity, Long id) {

        Activity _activity = eventRepository.findById(id).map((Event event) -> {
            event.getActivites().add(activity);
            return activityRepository.save(activity);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found event with id = " + id));
        return _activity;
    }

    @Override
    public List<Activity> listActivityByEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found event with id = " + id));
        List<Activity> activities = new ArrayList<Activity>();
        activities.addAll(event.getActivites());

        return activities;
    }
}
