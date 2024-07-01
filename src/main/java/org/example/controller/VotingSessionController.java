package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.entity.VoteResult;
import org.example.entity.VotingSession;
import org.example.service.VotingSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voting-sessions")
@Api(value = "VotingSession Management System")
public class VotingSessionController {
    @Autowired
    private VotingSessionService votingSessionService;

    @ApiOperation(value = "Create a new votingSession")
    @PostMapping("/agenda/{agendaId}")
    public ResponseEntity<VotingSession> createVotingSession(@PathVariable Long agendaId,
                                                             @RequestBody VotingSession votingSession) {
        VotingSession createdVotingSession = votingSessionService.createVotingSession(agendaId, votingSession);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVotingSession);
    }

    @ApiOperation(value = "Get votingSession by id")
    @GetMapping("/{id}")
    public ResponseEntity<VotingSession> getVotingSession(@PathVariable Long id) {
        VotingSession votingSession = votingSessionService.findVotingSessionById(id);
        return new ResponseEntity<>(votingSession, HttpStatus.OK);
    }

    @ApiOperation(value = "Get all votingSession")
    @GetMapping
    public ResponseEntity<List<VotingSession>> getAllVotingSessions() {
        List<VotingSession> votingSessions = votingSessionService.findAllVotingSessions();
        return new ResponseEntity<>(votingSessions, HttpStatus.OK);
    }

    @GetMapping("/{sessionId}/result")
    public ResponseEntity<VoteResult> getResult(@PathVariable Long sessionId) {
        VoteResult result = votingSessionService.getResult(sessionId);
        return ResponseEntity.ok(result);
    }
}
