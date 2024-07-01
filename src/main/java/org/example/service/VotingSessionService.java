package org.example.service;

import org.example.entity.Agenda;
import org.example.entity.VoteResult;
import org.example.entity.VotingSession;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.VotingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotingSessionService {
    @Autowired
    private VotingSessionRepository votingSessionRepository;

    @Autowired
    private VoteService voteService;

    @Autowired
    private AgendaService agendaService;

    public VotingSession createVotingSession(Long agendaId, VotingSession votingSession) {
        Agenda agenda = agendaService.findAgendaById(agendaId);
        votingSession.setAgenda(agenda);
        votingSession.setStartDate(LocalDateTime.now());
        votingSession.setEndDate(votingSession.getEndDate() != null ? votingSession.getEndDate()
                : LocalDateTime.now().plusMinutes(1));
        return votingSessionRepository.save(votingSession);
    }

    public VotingSession findVotingSessionById(Long id) {
        return votingSessionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Sessão de votação não encontrada com id  " + id));
    }

    public List<VotingSession> findAllVotingSessions() {
        return votingSessionRepository.findAll();
    }

    public VoteResult getResult(Long sessionId) {
             VotingSession session = votingSessionRepository.findById(sessionId)
                     .orElseThrow(() -> new ResourceNotFoundException("Sessão de votação não encontrada"));
        return voteService.countVotes(sessionId);
    }
}
