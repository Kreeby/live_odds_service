package com.coding.live_odds_service.handlers;

import com.coding.live_odds_service.models.Match;
import com.coding.live_odds_service.models.Summary;
import com.coding.live_odds_service.models.Team;

import java.util.List;

public interface MatchHandler {

    void startNewMatch(Team homeTeam, Team awayTeam);
    void updateScore(Match match, int homeScore, int awayScore);

    Match getCurrentMatchInProgress();

    void finishMatch(Match matchInProgress);

    List<Summary> getMatchSummaries();
}