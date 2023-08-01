package com.coding.live_odds_service.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Match {
    Team homeTeam;
    Team awayTeam;

    LocalDateTime startDate;

    public Match(Team homeTeam, Team awayTeam, LocalDateTime localDateTime) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.startDate = localDateTime;
    }
}
