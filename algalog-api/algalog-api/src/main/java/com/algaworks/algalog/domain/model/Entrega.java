package com.algaworks.algalog.domain.model;

import com.algaworks.algalog.domain.exception.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid //validar o objeto cliente
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded //abstrair dados
    private Destinatario destinatario;


    @NotNull
    private BigDecimal taxa;

    @Enumerated(EnumType.STRING) //salva no bd a string
    private StatusEntrega status;

    private OffsetDateTime dataPedido;

    private OffsetDateTime dataFinalizacao;

    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);
        this.getOcorrencias().add(ocorrencia);
        return ocorrencia;
    }

    public void finalizar() {
        if (naoPodeSerFinalizada()) {
            throw new DomainException("Entrega não pode ser finalizada");
        }
        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public void cancelar() {
        if (naoPodeSerFinalizada()) {
            throw new DomainException("Entrega não pode ser Cancelada");
        }
        setStatus(StatusEntrega.CANCELADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean naoPodeSerFinalizada() {
        return !StatusEntrega.PENDENTE.equals(getStatus());
    }


}
