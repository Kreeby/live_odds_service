package com.coding.live_odds_service.handlers.impl;

import com.coding.live_odds_service.handlers.MatchHandler;
import com.coding.live_odds_service.models.Match;
import com.coding.live_odds_service.models.Scoreboard;
import com.coding.live_odds_service.models.Summary;
import com.coding.live_odds_service.models.Team;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatchHandlerImpl implements MatchHandler {

    Scoreboard scoreboard;

    public MatchHandlerImpl(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public Match startNewMatch(Team homeTeam, Team awayTeam) {
        Match match = new Match(homeTeam, awayTeam, LocalDateTime.now(), false);
        scoreboard.addMatchesInProgress(match);
        return match;
    }

    @Override
    public Match updateScore(Match match, int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("The score cannot be less than 0");
        }
        if (match.isFinished()) {
            throw new IllegalStateException("Cannot update the score for a finished match");
        }
        match.getHomeTeam().setScore(homeScore);
        match.getAwayTeam().setScore(awayScore);

        return match;
    }

    @Override
    public void finishMatch(Match matchInProgress) {
        scoreboard.removeMatchInProgress(matchInProgress);
    }

    @Override
    public List<Summary> getMatchSummaries() {
        List<Summary> summaries = new ArrayList<>();

        for (Match match: scoreboard.getMatchesInProgress()) {
            int totalScore = match.getHomeTeam().getScore() + match.getAwayTeam().getScore();
            Summary summary = new Summary(match, totalScore, match.getStartDate());
            summaries.add(summary);
        }

        sortSummaries(summaries);

        return summaries;
    }

    private void sortSummaries(List<Summary> summaries) {
        summaries.sort((s1, s2) -> {
            int scoreComp = Integer.compare(s2.getTotalScore(), s1.getTotalScore());
            if (scoreComp != 0) {
                return scoreComp;
            }
            return s2.getStartTime().compareTo(s1.getStartTime());
        });
    }
}
