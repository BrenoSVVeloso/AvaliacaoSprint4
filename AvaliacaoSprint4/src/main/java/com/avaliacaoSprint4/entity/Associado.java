package com.avaliacaoSprint4.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.avaliacaoSprint4.entity.FolderEnum.CargoPolitico;
import com.avaliacaoSprint4.entity.FolderEnum.Sexo;

import lombok.Data;


@Data
@Entity
public class Associado {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CargoPolitico cargo;

    @NotNull
    private String DATADENASCIMENTO;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @NotNull
    private String partido;

}
