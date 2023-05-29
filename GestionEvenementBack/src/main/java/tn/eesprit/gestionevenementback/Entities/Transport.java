package tn.eesprit.gestionevenementback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Transport implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer TransportId;
    String FromAdress;
    Float Price;
    Integer Capacity;
    @Enumerated(EnumType.STRING)
    TransportType transportType;

    @ManyToOne
    ServiceClient serviceClient;

}
