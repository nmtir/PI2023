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
public class Payement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer PayementId;

    @OneToOne(mappedBy = "payement",cascade = CascadeType.REMOVE)
    Facture facture;
    @OneToOne
    Logistique logistique;
    @OneToOne(cascade = CascadeType.PERSIST)
    Reservation reservation;

}
