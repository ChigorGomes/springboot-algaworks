package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
    private RegistroOcorrenciaService registroOcorrenciaService;
    private EntregaRepository entregaRepository;

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = registroOcorrenciaService.buscarEntrega(entregaId);
        entrega.finalizar();
        entrega.setStatus(StatusEntrega.FINALIZADA);
        entregaRepository.save(entrega);
    }

    @Transactional
    public void cancelar(Long entregaId) {
        Entrega entrega = registroOcorrenciaService.buscarEntrega(entregaId);
        entrega.cancelar();
        entrega.setStatus(StatusEntrega.CANCELADA);
        entregaRepository.save(entrega);
    }
}
