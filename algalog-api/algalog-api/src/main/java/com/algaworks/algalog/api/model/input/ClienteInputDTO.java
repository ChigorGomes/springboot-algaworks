package com.algaworks.algalog.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteInputDTO {
    @NotNull
    private String nome;
    @NotNull
    private String email;
    @NotNull
    private String telefone;
}
