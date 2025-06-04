package com.example.Laboratorio_service.micro.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Laboratorio_service.micro.dto.LaboratorioDTO;
import com.example.Laboratorio_service.micro.model.service.LaboratorioService;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratoryService;

    public LaboratorioController(LaboratorioService laboratoryService) {
		super();
		this.laboratoryService = laboratoryService;
	}

	@GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('PROFESSOR')")
    public ResponseEntity<List<LaboratorioDTO>> getAllLaboratories() {
        List<LaboratorioDTO> laboratories = laboratoryService.getAll();
        return ResponseEntity.ok(laboratories);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LaboratorioDTO> createLaboratory(@RequestBody LaboratorioDTO laboratoryDTO) {
        LaboratorioDTO savedLaboratory = laboratoryService.create(laboratoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLaboratory);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteLaboratory(@PathVariable Long id) {
        laboratoryService.delete(id);
        return ResponseEntity.ok().build();
    }
}
