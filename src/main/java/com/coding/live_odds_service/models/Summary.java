package com.coding.live_odds_service.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Summary {
    Match match;
    int totalScore;
    LocalDateTime startTime;
}
