package com.example.Laboratorio_service.micro.model.entity;

import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Laboratorio {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private String status;

 
    @ElementCollection
    @CollectionTable(
        name = "laboratory_software",
        joinColumns = @JoinColumn(name = "laboratory_id")
    )
    @Column(name = "software_id")
    private List<Long> softwaresIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Long> getSoftwaresIds() {
		return softwaresIds;
	}

	public void setSoftwaresIds(List<Long> softwaresIds) {
		this.softwaresIds = softwaresIds;
	}


	
	
}
