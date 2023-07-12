package tn.eesprit.gestionevenementback.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;



    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "events",
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = "title")
            })
    public class Event {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Lob
        @Column(name = "imagedata",length = 1000)
        private byte[] imageData;
        private String title;
        private Date startDate;
        private Date endDate;
        private String description;
        private String lieu;
        private EventType type;
        private String lang;
        private String lat;
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
        @JoinColumn(name = "event_id")
        private Set<Activity> activites = new HashSet<>();
        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
        @JoinColumn(name = "like_id")
        private Set<User> likes = new HashSet<>();

        @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
        @JoinColumn(name = "deslike_id")
        private Set<User> deslikes = new HashSet<>();
        public Event(String title,String description,Date startDate,Date endDate,String lieu,
                     String type,String lang,String lat, byte[] imageData){
            this.title=title;
            this.description=description;
            this.startDate=startDate;
            this.endDate=endDate;
            this.lieu=lieu;
            this.type= EventType.valueOf(type);
            this.lang=lang;
            this.lat=lat;
            this.imageData=imageData;
        }
    }
