package tn.eesprit.gestionevenementback.Entities;

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
public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer forumId;

    @OneToOne (cascade = CascadeType.PERSIST)
    Event event;

    @OneToMany(mappedBy = "forum",cascade = CascadeType.REMOVE)
    Set<Post> posts;
}
