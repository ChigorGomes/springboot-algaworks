package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.assembler.EntregaMapper;
import com.algaworks.algalog.api.model.EntregaDTO;
import com.algaworks.algalog.api.model.input.EntregaInputDTO;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private FinalizacaoEntregaService finalizacaoEntregaService;
    private EntregaMapper entregaMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaDTO solicitarEntrega(@Valid @RequestBody EntregaInputDTO entregaInputDTO) {
        Entrega novaEntrega = entregaMapper.toEntity(entregaInputDTO);
        Entrega solicitarEntrega = solicitacaoEntregaService.solicitar(novaEntrega);
        return entregaMapper.toModel(solicitarEntrega);
    }

    @GetMapping
    public List<EntregaDTO> listar() {
        return entregaMapper.toCollectionModel(solicitacaoEntregaService.listar());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
        EntregaDTO entregaDTO = entregaMapper.toModel(solicitacaoEntregaService.buscar(entregaId));
        return ResponseEntity.ok(entregaDTO);
    }

    @PutMapping("/{entregaId}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.finalizar(entregaId);
    }

    @PutMapping("/{entregaId}/cancelar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelar(@PathVariable Long entregaId) {
        finalizacaoEntregaService.cancelar(entregaId);
    }
}
