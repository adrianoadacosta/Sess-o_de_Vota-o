package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<VotingSession> sessions;

    public Agenda(){
    }

    public Agenda(Long id, String title, String description, List<VotingSession> sessions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.sessions = sessions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<VotingSession> getSessions() {
        return sessions;
    }

    public void setSessions(List<VotingSession> sessions) {
        this.sessions = sessions;
    }
}
