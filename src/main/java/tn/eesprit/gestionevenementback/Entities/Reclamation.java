package tn.eesprit.gestionevenementback.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subject;
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    Date date;

    public Reclamation(String subject,String content){

        this.subject=subject;
        this.content=content;
    }


}
