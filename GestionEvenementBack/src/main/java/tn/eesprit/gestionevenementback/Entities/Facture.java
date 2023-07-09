package tn.eesprit.gestionevenementback.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
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
    Long factureId;

    Epayement payement;
    @Temporal(TemporalType.TIME)
    Date  dateFacture;
    private double sum;

    public Facture(Epayement epayement,double sum){
        this.payement=epayement;
        this.sum=sum;
    }
    @JsonIgnore
    @OneToOne
    Payement payementId;


}
