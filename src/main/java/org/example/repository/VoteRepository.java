package org.example.repository;

import org.example.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
   // List<Vote> findByVotingSessionId(Long votingSessionId);
    List<Vote> findByVotingSessionId(Long sessionId);

    Optional<Vote> findByVotingSessionIdAndAssociateId(Long sessionId, Long associateId);
}
