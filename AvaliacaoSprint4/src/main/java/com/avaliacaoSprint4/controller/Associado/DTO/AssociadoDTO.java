package com.avaliacaoSprint4.controller.Associado.DTO;

import javax.validation.constraints.NotNull;

import com.avaliacaoSprint4.entity.FolderEnum.CargoPolitico;
import com.avaliacaoSprint4.entity.FolderEnum.Sexo;


import lombok.Data;
@Data
public class AssociadoDTO {
    
    @NotNull
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private CargoPolitico cargo;
    @NotNull
    private String DATADENASCIMENTO;
    @NotNull
    private Sexo sexo;

    @NotNull
    private String partido;


}
