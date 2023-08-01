package com.coding.live_odds_service;


import com.coding.live_odds_service.handlers.impl.MatchHandlerImpl;
import com.coding.live_odds_service.models.Match;
import com.coding.live_odds_service.models.Scoreboard;
import com.coding.live_odds_service.models.Summary;
import com.coding.live_odds_service.models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchHandlerTest {

    Scoreboard scoreboard;
    MatchHandlerImpl matchHandler;

    @BeforeEach
    void setUp() {
        scoreboard = Mockito.mock(Scoreboard.class);
        matchHandler = new MatchHandlerImpl(scoreboard);
    }

    @Test
    void test_startNewMatch() {
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        matchHandler.startNewMatch(homeTeam, awayTeam);

        Mockito.verify(scoreboard).addMatchesInProgress(Mockito.any(Match.class));
    }

    @Test
    void test_updateScore() {
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        matchHandler.startNewMatch(homeTeam, awayTeam);

        homeTeam.setScore(1);
        awayTeam.setScore(2);

        Match matchInProgress = matchHandler.getCurrentMatchInProgress();

        matchHandler.updateScore(matchInProgress, homeTeam.getScore(), awayTeam.getScore());

        matchInProgress = matchHandler.getCurrentMatchInProgress();

        assertEquals(homeTeam.getScore(), matchInProgress.getHomeTeam().getScore());
        assertEquals(awayTeam.getScore(), matchInProgress.getAwayTeam().getScore());
    }

    @Test
    void test_finishTheMatch() {
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        matchHandler.startNewMatch(homeTeam, awayTeam);

        Match matchInProgress = matchHandler.getCurrentMatchInProgress();

        matchHandler.finishMatch(matchInProgress);

        Mockito.verify(scoreboard).removeMatchInProgress(Mockito.eq(matchInProgress));
    }

    @Test
    public void testGetMatchSummariesOrderedByScoreAndStartTime() {
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        Team homeTeam1 = new Team();
        Team awayTeam1 = new Team();

        Team homeTeam2 = new Team();
        Team awayTeam2 = new Team();


        Match match = new Match(homeTeam, awayTeam);
        matchHandler.startNewMatch(match.getHomeTeam(), match.getAwayTeam());
        matchHandler.updateScore(match, 1, 0);

        Match match2 = new Match(homeTeam1, awayTeam1);
        matchHandler.startNewMatch(match2.getHomeTeam(), match2.getAwayTeam());
        matchHandler.updateScore(match2, 2, 2);

        Match match3 = new Match(homeTeam2, awayTeam2);
        matchHandler.startNewMatch(match3.getHomeTeam(), match3.getAwayTeam());
        matchHandler.updateScore(match3, 3, 1);

        List<Summary> summaries = matchHandler.getMatchSummaries();


        assertEquals(match3, summaries.get(0).getMatch());
        assertEquals(match2, summaries.get(1).getMatch());
        assertEquals(match, summaries.get(2).getMatch());
    }


}
