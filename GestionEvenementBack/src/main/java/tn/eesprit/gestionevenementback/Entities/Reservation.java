package tn.eesprit.gestionevenementback.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer reservationId;
    Boolean transportIncluded;
    String transportStartingAdress;
    Boolean housingIncluded;
    Boolean seated;
    Float price;
    @OneToOne(mappedBy = "reservation",cascade = CascadeType.REMOVE)
    Payement payement;
    @JsonIgnore
    @ManyToOne
    Event event;
    @ManyToOne
    User user;
    @JsonIgnore
    @ManyToOne
    Transport transport;
}
