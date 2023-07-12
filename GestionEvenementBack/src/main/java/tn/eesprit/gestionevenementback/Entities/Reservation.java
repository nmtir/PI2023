package tn.eesprit.gestionevenementback.Entities;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Reservation {
    @Id
    @GeneratedValue
      Long id;
      String status="Not confirmed";
    @OneToOne
     User user;
    @OneToOne
    Event event;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "reservation_id")
    Set<Activity> activites = new HashSet<>();
    Date dateReservation  ;

public Reservation(User user,Event event,Set<Activity> activites ){
    this.user=user;
    this.activites=activites;
    this.event=event;
    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    Date dateReservation = new Date(System.currentTimeMillis());
    this.dateReservation= dateReservation;
}

}
