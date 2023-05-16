package tn.eesprit.gestionevenementback.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ReservationId;



    @OneToOne(mappedBy = "reservation",cascade = CascadeType.REMOVE)
    Payement payement;
    @ManyToOne
    ServiceClient serviceClient;
    @ManyToOne(cascade = CascadeType.PERSIST)
    Event event;
    @ManyToOne(cascade = CascadeType.PERSIST)
    User user;
}
