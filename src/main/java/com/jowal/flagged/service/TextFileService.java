package com.jowal.flagged.service;

import com.jowal.flagged.dto.BadWordsCheckResponse;
import com.jowal.flagged.repository.TextFileRepository;
import com.jowal.flagged.model.TextFile;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Slf4j
@Service
public class TextFileService {

    private final TextFileRepository textFileRepository;

    public TextFileService(TextFileRepository textFileRepository) {
        this.textFileRepository = textFileRepository;
    }

    public TextFile saveFile(MultipartFile file) throws IOException {
        String content = new String(file.getBytes(), StandardCharsets.UTF_8);

        TextFile textFile = new TextFile();
        textFile.setName(file.getOriginalFilename());
        textFile.setUploadDate(LocalDateTime.now());
        textFile.setContent(content);

        return textFileRepository.save(textFile);
    }
    public String readTextFile(MultipartFile file) {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error("Error reading file: {}", file.getOriginalFilename(), e);
            throw new RuntimeException("Error reading file", e);
        }

        return contentBuilder.toString();
    }
    public List<String> checkAgainstFiles(String input) {
        String[] words = input.toLowerCase(Locale.ROOT).split(",");
        List<String> foundWords = new ArrayList<>();

        for (String word : words) {
            word = word.trim();
            if (isWordInFiles(word)) {
                foundWords.add(word);
            }
        }

        return foundWords;
    }

    private boolean isWordInFiles(String word) {
        for (TextFile file : textFileRepository.findAll()) {
            if (file.getContent().contains(word)) {
                return true;
            }
        }
        return false;
    }
    @Transactional
    public BadWordsCheckResponse checkAgainstMostRecentFile(String input) {
        List<String> inputWords = Arrays.asList(input.toLowerCase(Locale.ROOT).split(","));
        TextFile mostRecentFile = textFileRepository.findTopByOrderByUploadDateDesc();

        List<String> foundWords = new ArrayList<>();
        for (String word : inputWords) {
            word = word.trim();
            if (mostRecentFile.getContent().toLowerCase(Locale.ROOT).contains(word)) {
                foundWords.add(word);
            }
        }

        BadWordsCheckResponse response = new BadWordsCheckResponse();
        response.setInputWords(inputWords);
        response.setTextCheckedAgainst(mostRecentFile.getContent());
        response.setFoundBadWords(foundWords);
        response.setNumberOfBadWordsFound(foundWords.size());
        response.setCheckTime(LocalDateTime.now());

        return response;
    }
}



//}
