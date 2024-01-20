package com.jowal.flagged.controller;

import com.jowal.flagged.dto.StoredFilesResponse;
import com.jowal.flagged.model.TextFile;
import com.jowal.flagged.dto.FileUploadResponse;
import com.jowal.flagged.dto.BadWordsCheckResponse;
import com.jowal.flagged.service.TextFileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/words")
public class TextFileController {

    private final TextFileService textFileService;

    public TextFileController(TextFileService textFileService) {
        this.textFileService = textFileService;
    }

    @PostMapping("/upload") // http://localhost:8070/api/words/upload
    public ResponseEntity<FileUploadResponse> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            TextFile savedFile = textFileService.saveFile(file);
            String fileContent = textFileService.readTextFile(file);
            FileUploadResponse response = new FileUploadResponse(
                    "Success",
                    savedFile.getName(),
                    savedFile.getUploadDate(),
                    fileContent
            );
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error reading file", e);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error processing file", e);
        }
    }

    @PostMapping("/check") //http://localhost:8070/api/words/check
    public ResponseEntity<BadWordsCheckResponse> checkWords(@RequestBody String input) {
        BadWordsCheckResponse response = textFileService.checkAgainstMostRecentFile(input);
        return ResponseEntity.ok(response);
    }
    @GetMapping("uploadedfiles")  //http://localhost:8070/api/words/uploadedfiles
    public ResponseEntity<List<StoredFilesResponse>> getAllStoredFiles() {
        List<StoredFilesResponse> files = textFileService.getAllStoredFiles();
        return ResponseEntity.ok(files);
    }
}
