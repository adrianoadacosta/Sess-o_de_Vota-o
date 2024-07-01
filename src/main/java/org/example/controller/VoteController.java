package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.entity.Vote;
import org.example.entity.VoteResult;
import org.example.enums.VoteType;
import org.example.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/votes")
@Api(value = "Vote Management System")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @ApiOperation(value = "Create a new vote")
    @PostMapping
    public ResponseEntity<Vote> create(@RequestBody Vote vote) {
        return ResponseEntity.ok(voteService.save(vote));
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<Vote>> getBySession(@PathVariable Long sessionId) {
        return ResponseEntity.ok(voteService.findByVotingSessionId(sessionId));
    }

    @PostMapping("/session/{sessionId}/associate/{associateId}")
    public ResponseEntity<String> castVote(
            @PathVariable Long sessionId,
            @PathVariable Long associateId,
            @RequestBody Vote vote) {

       if (vote.getVote() != VoteType.SIM && vote.getVote() != VoteType.NAO) {
            return ResponseEntity.badRequest().body("Voto inválido. Use 'SIM' ou 'NAO'.");
        }

       if (voteService.hasAlreadyVoted(sessionId, associateId)) {
            return ResponseEntity.badRequest().body("Associado já votou nesta sessão.");
        }

        voteService.saveVote(sessionId, associateId, vote);

        return ResponseEntity.status(201).body("Voto registrado com sucesso.");
    }

    @GetMapping("/session/{sessionId}/result")
    public ResponseEntity<VoteResult> getResult(@PathVariable Long sessionId) {
        return ResponseEntity.ok(voteService.countVotes(sessionId));
    }
}
