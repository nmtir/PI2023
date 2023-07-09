package tn.eesprit.gestionevenementback.Entities;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer messageId;
    Date datePub;
    String contenu;
    Integer isBlocked;
    @ManyToMany
    Set<User> likes;

    @ManyToMany
    Set<User> signalUsers;
    @JsonIgnore
    @ManyToOne
    Message message;
    @OneToMany(mappedBy = "message")
    Set<Message> messages;
    @JsonIgnore
    @ManyToOne
    Post post;

    @ManyToOne
    User user;
}
