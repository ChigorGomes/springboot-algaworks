package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CatalogoClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream().anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if (emailEmUso) {
            throw new DomainException("E-mail já cadastrado!");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long clienteId) {
        Cliente cliente = buscar(clienteId);
        clienteRepository.deleteById(cliente.getId());

    }

    @Transactional
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscar(Long clienteId) {
        return clienteRepository.findById(clienteId).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente atualizar(Long clienteId, Cliente cliente) {
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()).stream().anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if (emailEmUso) {
            throw new DomainException("E-mail já cadastrado!");
        }
        cliente.setId(clienteId);
        return clienteRepository.save(cliente);
    }


}
