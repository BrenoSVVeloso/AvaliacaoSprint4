package com.avaliacaoSprint4.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.avaliacaoSprint4.entity.FolderEnum.Ideologia;

import lombok.Data;

@Data
@Entity
public class Partido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String sigla;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Ideologia ideologia;

    @NotNull
    private String DATAFUNDACAO = LocalDateTime.now().toString();
}
