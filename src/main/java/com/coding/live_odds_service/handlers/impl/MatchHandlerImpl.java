package com.coding.live_odds_service.handlers.impl;

import com.coding.live_odds_service.handlers.MatchHandler;
import com.coding.live_odds_service.models.Match;
import com.coding.live_odds_service.models.Scoreboard;
import com.coding.live_odds_service.models.Summary;
import com.coding.live_odds_service.models.Team;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MatchHandlerImpl implements MatchHandler {

    Scoreboard scoreboard;

    public MatchHandlerImpl(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    @Override
    public Match startNewMatch(Team homeTeam, Team awayTeam) {
        return null;
    }

    @Override
    public Match updateScore(Match match, int homeScore, int awayScore) {
        return null;
    }

    @Override
    public Match getCurrentMatchInProgress() {
        return null;
    }

    @Override
    public void finishMatch(Match matchInProgress) {

    }

    @Override
    public List<Summary> getMatchSummaries() {
        return null;
    }
}
