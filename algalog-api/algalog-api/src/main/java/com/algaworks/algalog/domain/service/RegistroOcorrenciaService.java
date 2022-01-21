package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {
    private EntregaRepository entregaRepository;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        Entrega entrega = buscarEntrega(entregaId);
        return entrega.adicionarOcorrencia(descricao);
    }

    public Entrega buscarEntrega(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

    public List<Ocorrencia> listarOcorrencias(Long entregaId) {
        Entrega entrega = buscarEntrega(entregaId);
        return entrega.getOcorrencias();
    }

}
