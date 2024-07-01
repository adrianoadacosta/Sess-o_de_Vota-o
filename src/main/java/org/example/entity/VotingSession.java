package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class VotingSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "agenda_id")
    @JsonBackReference
    private Agenda agenda;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    @OneToMany(mappedBy = "votingSession", cascade = CascadeType.ALL)
   // @JsonIgnore
    @JsonManagedReference
    List<Vote> votes;

    public VotingSession() {
    }

    public VotingSession(Long id, Agenda agenda, LocalDateTime startDate, LocalDateTime endDate,
                         List<Vote> votes) {
        this.id = id;
        this.agenda = agenda;
        this.startDate = startDate;
        this.endDate = endDate;
        this.votes = votes;
    }

    public VotingSession(Long sessionId) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
