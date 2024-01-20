package com.jowal.flagged.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponse {
    private String status;
    private String fileName;
    private LocalDateTime uploadDate;
    private String contents;
}
