package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.assembler.OcorrenciaMapper;
import com.algaworks.algalog.api.model.OcorrenciaDTO;
import com.algaworks.algalog.api.model.input.OcorrenciaInputDTO;
import com.algaworks.algalog.domain.model.Ocorrencia;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaMapper ocorrenciaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInputDTO ocorrenciaInputDTO) {
        Ocorrencia ocorrencia = registroOcorrenciaService.registrar(entregaId, ocorrenciaInputDTO.getDescricao());
        return ocorrenciaMapper.toModel(ocorrencia);
    }

    @GetMapping
    public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
        return ocorrenciaMapper.toCollectionModel(registroOcorrenciaService.listarOcorrencias(entregaId));
    }
}
