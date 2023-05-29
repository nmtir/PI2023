package tn.eesprit.gestionevenementback.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ResourceMateriel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ResourceId;
    Date dateLimitCommande;




    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    Logistique logistique;
    @ManyToMany(mappedBy = "resources")
    Set<Goods> goods;

}
