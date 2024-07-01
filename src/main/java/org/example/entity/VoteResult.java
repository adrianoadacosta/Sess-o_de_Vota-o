package org.example.entity;

public class VoteResult {
    private Long sessionId;
    private long totalVotes;
    private long yesVotes;
    private long noVotes;

    public VoteResult() {
    }

    public VoteResult(Long sessionId, long totalVotes, long yesVotes, long noVotes) {
        this.sessionId = sessionId;
        this.totalVotes = totalVotes;
        this.yesVotes = yesVotes;
        this.noVotes = noVotes;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    public long getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public long getYesVotes() {
        return yesVotes;
    }

    public void setYesVotes(long yesVotes) {
        this.yesVotes = yesVotes;
    }

    public long getNoVotes() {
        return noVotes;
    }

    public void setNoVotes(long noVotes) {
        this.noVotes = noVotes;
    }
}
