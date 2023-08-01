package com.coding.live_odds_service;


import com.coding.live_odds_service.handlers.impl.MatchHandlerImpl;
import com.coding.live_odds_service.models.Match;
import com.coding.live_odds_service.models.Scoreboard;
import com.coding.live_odds_service.models.Summary;
import com.coding.live_odds_service.models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

        Match currentMatch = matchHandler.startNewMatch(homeTeam, awayTeam);

        homeTeam.setScore(1);
        awayTeam.setScore(2);

        currentMatch = matchHandler.updateScore(currentMatch, homeTeam.getScore(), awayTeam.getScore());

        assertEquals(homeTeam.getScore(), currentMatch.getHomeTeam().getScore());
        assertEquals(awayTeam.getScore(), currentMatch.getAwayTeam().getScore());
    }

    @Test
    void test_finishTheMatch() {
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        Match currentMatch = matchHandler.startNewMatch(homeTeam, awayTeam);


        matchHandler.finishMatch(currentMatch);

        Mockito.verify(scoreboard).removeMatchInProgress(Mockito.eq(currentMatch));
    }

    @Test
    public void test_getMatchSummaries() {
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        Team homeTeam1 = new Team();
        Team awayTeam1 = new Team();

        Team homeTeam2 = new Team();
        Team awayTeam2 = new Team();


        Match match = new Match(homeTeam, awayTeam, LocalDateTime.of(2022, 10, 11, 12, 30), false);
        matchHandler.startNewMatch(match.getHomeTeam(), match.getAwayTeam());
        matchHandler.updateScore(match, 1, 0);

        Match match1 = new Match(homeTeam1, awayTeam1, LocalDateTime.of(2022, 10, 11, 13, 30), false);
        matchHandler.startNewMatch(match1.getHomeTeam(), match1.getAwayTeam());
        matchHandler.updateScore(match1, 2, 2);

        Match match2 = new Match(homeTeam2, awayTeam2, LocalDateTime.of(2022, 10, 11, 14, 30), false);
        matchHandler.startNewMatch(match2.getHomeTeam(), match2.getAwayTeam());
        matchHandler.updateScore(match2, 3, 1);

        List<Match> matches = new ArrayList<>();
        matches.add(match);
        matches.add(match1);
        matches.add(match2);

        Mockito.when(scoreboard.getMatchesInProgress()).thenReturn(matches);

        List<Summary> actualSummaries = matchHandler.getMatchSummaries();

        assertEquals(match2.getStartDate(), actualSummaries.get(0).getMatch().getStartDate());
        assertEquals(match1.getStartDate(), actualSummaries.get(1).getMatch().getStartDate());
        assertEquals(match.getStartDate(), actualSummaries.get(2).getMatch().getStartDate());
    }


    @Test
    public void test_updateScoreForFinishedMatch() {
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        Match match = new Match(homeTeam, awayTeam, Mockito.any(), true);
        matchHandler.startNewMatch(match.getHomeTeam(), match.getAwayTeam());
        matchHandler.finishMatch(match);

        int homeScore = 2;
        int awayScore = 1;
        assertThrows(IllegalStateException.class, () -> matchHandler.updateScore(match, homeScore, awayScore));

        assertEquals(0, match.getHomeTeam().getScore());
        assertEquals(0, match.getAwayTeam().getScore());
    }

    @Test
    public void test_invalidScore() {
        Team homeTeam = new Team();
        Team awayTeam = new Team();

        Match matchInProgress = matchHandler.startNewMatch(homeTeam, awayTeam);

        assertThrows(IllegalArgumentException.class, () -> {
            int homeScore = -1;
            int awayScore = 2;
            matchHandler.updateScore(matchInProgress, homeScore, awayScore);
        });

        // Verify that the match's score remains unchanged
        assertEquals(0, matchInProgress.getHomeTeam().getScore());
        assertEquals(0, matchInProgress.getAwayTeam().getScore());
    }

}
