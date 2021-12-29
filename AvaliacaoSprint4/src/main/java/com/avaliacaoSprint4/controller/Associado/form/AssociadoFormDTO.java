package com.avaliacaoSprint4.controller.Associado.form;



import javax.validation.constraints.NotNull;


import lombok.Data;
@Data
public class AssociadoFormDTO {
    @NotNull
    private String nome;
    @NotNull
    private String cargo;
    @NotNull
    private String DATADENASCIMENTO;
    @NotNull
    private String sexo;
}
