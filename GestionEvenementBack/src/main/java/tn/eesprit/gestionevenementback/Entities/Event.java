package tn.eesprit.gestionevenementback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer eventId;
    @Enumerated(EnumType.STRING)
    EventType eventType;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    Set<Activity> activities;
    @ManyToOne
    Category category;
    @JsonIgnore
    @OneToMany(mappedBy = "event",cascade = CascadeType.REMOVE)
    Set<Feedback> feedbacks;
    @JsonIgnore
    @OneToOne (mappedBy = "event",cascade = CascadeType.REMOVE)
    Logistique logistique;
    @JsonIgnore
    @OneToOne (mappedBy = "event",cascade = CascadeType.REMOVE)
    Forum forum;
    @JsonIgnore
    @OneToMany(mappedBy = "event",cascade = CascadeType.REMOVE)
    Set<Reservation> reservations;


}
