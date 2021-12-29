package com.avaliacaoSprint4.controller.Partido.form;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class PartidoFormDTO {

    @NotNull
    private String nome;

    @NotNull
    private String sigla;

    @NotNull
    private String ideologia;

    @NotNull
    private String DATAFUNDACAO;
}
