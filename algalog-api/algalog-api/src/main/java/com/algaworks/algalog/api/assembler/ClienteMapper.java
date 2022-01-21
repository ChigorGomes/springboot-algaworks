package com.algaworks.algalog.api.assembler;

import com.algaworks.algalog.api.model.ClienteDTO;
import com.algaworks.algalog.api.model.input.ClienteInputDTO;
import com.algaworks.algalog.domain.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteMapper {
    private ModelMapper modelMapper;

    public ClienteDTO toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDTO.class);
    }

    public List<ClienteDTO> toCollectionModel(List<Cliente> clientes) {
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Cliente toEntity(ClienteInputDTO clienteInputDTO) {
        return modelMapper.map(clienteInputDTO, Cliente.class);
    }
}
