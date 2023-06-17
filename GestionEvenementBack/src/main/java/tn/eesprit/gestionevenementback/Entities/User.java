package tn.eesprit.gestionevenementback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer userId;
    @Enumerated(EnumType.STRING)
    Role role;
    String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "views")
    Set<Post> viewedPosts;
    @JsonIgnore
    @ManyToMany(mappedBy = "likes")
    Set<Post> likedPosts;
    @JsonIgnore
    @ManyToMany(mappedBy = "likes")
    Set<Message> likedMessages;


    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    Set<Post> posts;
    @JsonIgnore
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    Set<Reservation> reservations;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    Set<Message> messages;
    @JsonIgnore
    @ManyToMany(mappedBy = "signalUsers")
    Set<Message> signaledMessages;
}
