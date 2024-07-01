package org.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.example.enums.VoteType;

import javax.persistence.*;

@Entity
@Table(name = "votes")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long associateId;
    @ManyToOne
    @JoinColumn(name = "voting_session_id")
    @JsonBackReference
    private VotingSession votingSession;
    @Enumerated(EnumType.STRING)
    private VoteType vote;

    public Vote() {
    }

    public Vote(Long id, Long associateId, VotingSession votingSession, VoteType vote) {
        this.id = id;
        this.associateId = associateId;
        this.votingSession = votingSession;
        this.vote = vote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAssociateId() {
        return associateId;
    }

    public void setAssociateId(Long associateId) {
        this.associateId = associateId;
    }

    public VotingSession getVotingSession() {
        return votingSession;
    }

    public void setVotingSession(VotingSession votingSession) {
        this.votingSession = votingSession;
    }

    public VoteType getVote() {
        return vote;
    }

    public void setVote(VoteType vote) {
        this.vote = vote;
    }

    public Long getAssociate() {
        return associateId;
    }
}
