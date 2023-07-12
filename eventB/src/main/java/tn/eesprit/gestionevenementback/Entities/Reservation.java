package tn.eesprit.gestionevenementback.Entities;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status="Not confirmed";
    @OneToOne
     User user;
    @OneToOne
    Event event;

    LocalDateTime  dateReservation;



public Reservation(User user,Event event ){
    this.user=user;
    this.event=event;
    this.dateReservation= LocalDateTime.now();
}

}
