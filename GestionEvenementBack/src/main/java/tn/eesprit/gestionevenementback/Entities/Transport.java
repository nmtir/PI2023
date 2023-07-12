package tn.eesprit.gestionevenementback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
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
    Integer transportId;
    String startAdress;
    Float price;
    Integer capacity;
    @Enumerated(EnumType.STRING)
    TransportType transportType;
    @ManyToOne
    Event event;
    @OneToMany(mappedBy = "transport")
    Set<Reservation> passangers;


}
