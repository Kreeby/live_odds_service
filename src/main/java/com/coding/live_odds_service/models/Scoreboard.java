package com.coding.live_odds_service.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Scoreboard {

    List<Match> matchesInProgress;


    public void addMatchesInProgress(Match match) {

    }

    public void removeMatchInProgress(Match match) {
    }
}
