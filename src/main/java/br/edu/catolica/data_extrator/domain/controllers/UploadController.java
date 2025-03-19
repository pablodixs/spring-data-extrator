package br.edu.catolica.data_extrator.domain.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.edu.catolica.data_extrator.domain.DTOs.FileResponse;
import br.edu.catolica.data_extrator.domain.services.FileProcessorService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UploadController {
    @Autowired
    private FileProcessorService fileProcessorService;

    @PostMapping("/upload")
    public ResponseEntity<List<FileResponse>> processFiles(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("dueDate") String dueDate) {

        try {
            MultipartFile[] filesArray = files.toArray(new MultipartFile[0]);
            List<FileResponse> filteredResults = this.fileProcessorService.process(filesArray, dueDate);
            return ResponseEntity.ok(filteredResults);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}