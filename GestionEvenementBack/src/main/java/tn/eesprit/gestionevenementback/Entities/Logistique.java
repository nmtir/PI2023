package tn.eesprit.gestionevenementback.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Logistique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer logistiqueId;
    Float depenses;
    Float totalDepenses;
    Float totalIncome;


    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    Event event;

    @OneToOne(mappedBy = "logistique",cascade = CascadeType.REMOVE)
    Payement payement;
    @JsonIgnore
    @OneToMany(mappedBy = "logistique")
    Set<Ordre> ordres;
}
