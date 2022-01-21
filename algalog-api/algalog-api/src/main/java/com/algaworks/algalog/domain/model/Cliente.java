package com.algaworks.algalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {


    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //forma nativa do banco de dados
    private Long id;

    @NotBlank()
    @Size(max = 60)
    @Column(name = "nome")
    private String nome;

    @NotBlank()
    @Email
    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @NotBlank()
    @Size(max = 60)
    @Column(name = "fone")
    private String telefone;


}
