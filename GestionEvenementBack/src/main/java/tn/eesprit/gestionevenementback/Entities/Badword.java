package tn.eesprit.gestionevenementback.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Badword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer badwordId;
    String word;
}
