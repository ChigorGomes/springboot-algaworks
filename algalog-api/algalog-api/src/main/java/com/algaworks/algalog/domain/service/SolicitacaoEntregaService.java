package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private CatalogoClienteService clienteService;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = clienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }

    @Transactional
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    @Transactional
    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId).orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não localizada"));
    }
}
