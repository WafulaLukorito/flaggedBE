package com.jowal.flagged.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoredFilesResponse {
    private Long id;
    private String name;
    private LocalDateTime uploadDate;
}
