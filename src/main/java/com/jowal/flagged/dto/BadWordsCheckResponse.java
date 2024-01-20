package com.jowal.flagged.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadWordsCheckResponse {
    private List<String> inputWords;
    private String textCheckedAgainst;
    private List<String> foundBadWords;
    private int numberOfBadWordsFound;
    private LocalDateTime checkTime;

}