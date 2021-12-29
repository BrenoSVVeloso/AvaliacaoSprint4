package com.avaliacaoSprint4.controller.Partido.DTO;


import javax.validation.constraints.NotNull;

import com.avaliacaoSprint4.entity.FolderEnum.Ideologia;

import lombok.Data;
@Data
public class PartidoDTO {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String sigla;

    @NotNull
    private Ideologia ideologia;
    
    @NotNull
    private String DATAFUNDACAO;
}
