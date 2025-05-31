package service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import entity.Laboratorio;
import Repository.LaboratorioRepository;
import client.SoftwareClient;
import dto.LaboratorioDTO;
import dto.SoftwareDTO;
import mapper.LaboratorioMapper;

@Service
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;
    private final SoftwareClient softwareClient;

    public LaboratorioService(LaboratorioRepository laboratorioRepository, SoftwareClient softwareClient) {
        this.laboratorioRepository = laboratorioRepository;
        this.softwareClient = softwareClient;
    }

    @Transactional(readOnly = true)
    public List<LaboratorioDTO> getAll() {
        return laboratorioRepository.findAll()
                .stream()
                .map(LaboratorioMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public LaboratorioDTO create(LaboratorioDTO dto) {
        Laboratorio lab = LaboratorioMapper.toEntity(dto);
        return LaboratorioMapper.toDTO(laboratorioRepository.save(lab));
    }

    @Transactional
    public void delete(Long id) {
        if (!laboratorioRepository.existsById(id)) {
            throw new RuntimeException("Laboratório não encontrado");
        }
        laboratorioRepository.deleteById(id);
    }

    public List<SoftwareDTO> getSoftwaresInstaladosById(Long laboratorioId) {
        Laboratorio lab = laboratorioRepository.findById(laboratorioId)
            .orElseThrow(() -> new RuntimeException("Laboratório não encontrado"));

        return lab.getSoftwaresIds().stream()
            .map(softwareClient::getSoftwareById)
            .collect(Collectors.toList());
    }
}
