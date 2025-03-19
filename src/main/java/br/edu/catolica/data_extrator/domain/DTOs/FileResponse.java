package br.edu.catolica.data_extrator.domain.DTOs;

public record FileResponse(
        String fileName,
        String dueDate,
        String issueDate,
        String date,
        String description) {

}
