package tn.eesprit.gestionevenementback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
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
public class ServiceClient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer ServiceClientId;
    Date DateLimiteHousing;
    Date DateLimitTransport;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    Logistique logistique;
    @JsonIgnore
    @OneToMany(mappedBy = "serviceC")
    Set<Housing> housings;
    @JsonIgnore
    @OneToMany(mappedBy = "serviceClient")
    Set<Transport> transports;

}
