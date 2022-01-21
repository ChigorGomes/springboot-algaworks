package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.assembler.ClienteMapper;
import com.algaworks.algalog.api.model.ClienteDTO;
import com.algaworks.algalog.api.model.input.ClienteInputDTO;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor // serve como autowired
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private CatalogoClienteService catalogoClienteService;
    private ClienteMapper clienteMapper;

    @GetMapping
    public List<ClienteDTO> listar() {
        return clienteMapper.toCollectionModel(catalogoClienteService.listarClientes());
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> buscaClienteId(@PathVariable Long clienteId) {
        ClienteDTO clienteDTO = clienteMapper.toModel(catalogoClienteService.buscar(clienteId));
        return ResponseEntity.ok(clienteDTO);

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO adicionar(@Valid @RequestBody ClienteInputDTO clienteInputDTO) {
        Cliente cliente = clienteMapper.toEntity(clienteInputDTO);
        Cliente novoCliente = catalogoClienteService.salvar(cliente);
        return clienteMapper.toModel(novoCliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long clienteId, @Valid @RequestBody ClienteInputDTO clienteInputDTO) {
        Cliente cliente = catalogoClienteService.buscar(clienteId);
        catalogoClienteService.atualizar(cliente.getId(), clienteMapper.toEntity(clienteInputDTO));
        return ResponseEntity.ok(clienteMapper.toModel(cliente));
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
        catalogoClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build(); //cod 204
    }
}
