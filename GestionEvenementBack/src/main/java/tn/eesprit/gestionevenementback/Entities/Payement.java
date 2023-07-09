package tn.eesprit.gestionevenementback.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Payement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer payementId;

    @OneToOne(mappedBy = "payementId")
    Facture facture;
    @JsonIgnore
    @OneToOne
    Logistique logistique;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST)
    Reservation reservation;

}
