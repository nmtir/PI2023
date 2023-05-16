package tn.eesprit.gestionevenementback.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class ResourceMateriel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ResourceId;




    @OneToOne
    Logistique logistique;
    @ManyToMany(mappedBy = "resources")
    Set<Bien> biens;

}
