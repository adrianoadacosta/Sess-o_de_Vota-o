package org.example.service;

import org.example.entity.Associate;
import org.example.entity.Vote;
import org.example.entity.VoteResult;
import org.example.entity.VotingSession;
import org.example.enums.VoteType;
import org.example.repository.AssociateRepository;
import org.example.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VotingSessionService votingSessionService;

    @Autowired
    private AssociateRepository associateRepository;

    public Vote save(Vote vote){
        Optional<Associate> associate = associateRepository.findById(vote.getAssociateId());
        if(!associate.isPresent()){
            throw new IllegalArgumentException("Associado não encontrado.");
        }
        Optional<Vote> existingVote = voteRepository
                .findByVotingSessionIdAndAssociateId(vote.getVotingSession().getId(),
                        vote.getAssociateId());
        if (existingVote.isPresent()){
            throw new IllegalArgumentException("Associado já votou nessa sessão.");
        }
        return voteRepository.save(vote);
    }

    public List<Vote> findByVotingSessionId(Long sessionId) {

        return voteRepository.findByVotingSessionId(sessionId);
    }

    public VoteResult countVotes(Long sessionId) {
        List<Vote> votes = voteRepository.findByVotingSessionId(sessionId);
        long yesVotes = votes.stream().filter(vote -> vote.getVote() == VoteType.SIM).count();
        long noVotes = votes.stream().filter(vote -> vote.getVote() == VoteType.NAO).count();
        return new VoteResult(sessionId, votes.size(), yesVotes, noVotes);
    }

   public boolean hasAlreadyVoted(Long sessionId, Long associateId) {
       Optional<Vote> votes = voteRepository.findByVotingSessionIdAndAssociateId(sessionId, associateId);
       return votes.isPresent();
   }

   public void saveVote(Long sessionId, Long associateId, Vote vote) {
       vote.setVotingSession(new VotingSession(sessionId));
       vote.setAssociateId(associateId);
       voteRepository.save(vote);
   }
}
