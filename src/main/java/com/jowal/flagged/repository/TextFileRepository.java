package com.jowal.flagged.repository;

import com.jowal.flagged.model.TextFile;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TextFileRepository extends JpaRepository<TextFile, Long> {

    TextFile findTopByOrderByUploadDateDesc();
}
