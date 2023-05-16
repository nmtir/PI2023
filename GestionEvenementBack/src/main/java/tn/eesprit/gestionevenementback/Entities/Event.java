package tn.eesprit.gestionevenementback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Event implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer EventId;
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

    @OneToOne (mappedBy = "event",cascade = CascadeType.REMOVE)
    Logistique logistique;
    @OneToOne (mappedBy = "event",cascade = CascadeType.REMOVE)
    Forum forum;
    @JsonIgnore
    @OneToMany(mappedBy = "event",cascade = CascadeType.REMOVE)
    Set<Reservation> reservations;


}
