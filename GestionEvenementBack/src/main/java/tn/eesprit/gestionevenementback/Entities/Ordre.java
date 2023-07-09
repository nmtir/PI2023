package tn.eesprit.gestionevenementback.Entities;
import javax.persistence.*;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Ordre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderId;
    Integer quantity;
    Date dateLimitCommande;
    @ManyToOne
    Logistique logistique;
    @ManyToOne
    Product product;
    @ManyToOne
    Transport transport;
    @ManyToOne
    Housing housing;


}