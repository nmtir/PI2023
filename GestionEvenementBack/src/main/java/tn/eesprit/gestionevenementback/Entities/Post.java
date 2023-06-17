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
public class Post implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer postId;
    String content;
    String title;
    Date datePub;
    @ManyToMany
    Set<User> likes;
    @ManyToMany
    Set<User> views;


    @JsonIgnore
    @ManyToOne
    Forum forum;

    @OneToMany(mappedBy = "post")
    Set<Message> messages;

    @ManyToOne
    User user;
}
