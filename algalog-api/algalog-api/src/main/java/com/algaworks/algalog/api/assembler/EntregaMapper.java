package com.algaworks.algalog.api.assembler;

import com.algaworks.algalog.api.model.EntregaDTO;
import com.algaworks.algalog.api.model.input.EntregaInputDTO;
import com.algaworks.algalog.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaMapper {
    private ModelMapper modelMapper;

    public EntregaDTO toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaDTO.class);
    }

    public List<EntregaDTO> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaInputDTO entregaInputDTO) {
        return modelMapper.map(entregaInputDTO, Entrega.class);
    }
}
