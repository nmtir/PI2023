package tn.eesprit.gestionevenementback.Entities;

import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Facture implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long FactureId;

    Epayement payement;
    LocalDateTime dateFacture;
    private double sum;

    public Facture(Epayement epayement,double sum){
        this.payement=epayement;
        this.sum=sum;
        this.dateFacture=LocalDateTime.now();
    }


}
