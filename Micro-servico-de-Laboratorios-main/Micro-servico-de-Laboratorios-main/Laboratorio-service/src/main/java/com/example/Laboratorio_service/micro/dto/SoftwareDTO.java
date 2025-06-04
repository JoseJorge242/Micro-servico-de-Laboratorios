package com.example.Laboratorio_service.micro.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SoftwareDTO {
	
    private Long id;
    private String nome;
    private String versao;
    private String link;
    private String tipo; // LIVRE ou PROPRIETARIO
    private boolean disponivel;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
    private LocalDateTime dataSolicitacao;

    public enum TipoSoftware {
        LIVRE, PROPRIETARIO
    }
}
