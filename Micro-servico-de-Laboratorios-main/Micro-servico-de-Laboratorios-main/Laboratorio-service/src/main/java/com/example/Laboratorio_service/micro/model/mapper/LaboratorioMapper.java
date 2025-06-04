package com.example.Laboratorio_service.micro.model.mapper;

import com.example.Laboratorio_service.micro.dto.LaboratorioDTO;
import com.example.Laboratorio_service.micro.model.entity.Laboratorio;

public class LaboratorioMapper {

    public static LaboratorioDTO toDTO(Laboratorio lab) {
        return new LaboratorioDTO(
            lab.getId(),
            lab.getNome(),
            lab.getStatus(),
            lab.getSoftwaresIds()
        );
    }

    public static Laboratorio toEntity(LaboratorioDTO dto) {
        Laboratorio lab = new Laboratorio();
        lab.setId(dto.getId());
        lab.setNome(dto.getName());
        lab.setStatus(dto.getStatus());
        lab.setSoftwaresIds(dto.getSoftwaresIds());
        return lab;
    }
}
