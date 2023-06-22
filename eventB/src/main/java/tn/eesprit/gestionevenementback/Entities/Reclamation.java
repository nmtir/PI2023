package tn.eesprit.gestionevenementback.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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


    Date dateReclamation;

    public Reclamation(String subject,String content){

        this.subject=subject;
        this.content=content;
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date dateReclamation = new Date(System.currentTimeMillis());
        this.dateReclamation= dateReclamation;
    }


}
