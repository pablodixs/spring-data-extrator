package br.edu.catolica.data_extrator.domain.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.catolica.data_extrator.domain.DTOs.FileResponse;

@Service
public class FileProcessorService {
    public List<FileResponse> process(MultipartFile[] files, String date) throws IOException {
        List<FileResponse> results = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.trim().split("\\s+");

                        if (parts.length >= 4) {
                            String description = String.join(" ",
                                    java.util.Arrays.copyOfRange(parts, 0, parts.length - 3));

                            String issueDate = parts[parts.length - 3];
                            String dueDate = parts[parts.length - 2];
                            String auxDate = parts[parts.length - 1];

                            if (dueDate.equals(date)) {
                                FileResponse response = new FileResponse(
                                        file.getOriginalFilename(),
                                        dueDate,
                                        issueDate,
                                        auxDate,
                                        description);
                                results.add(response);
                            }
                        }
                    }
                }
            }
        }

        return results;
    }
}