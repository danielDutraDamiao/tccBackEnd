package com.backend.backendtcc.service;

import com.backend.backendtcc.dto.ClienteDTO;
import com.backend.backendtcc.model.Cliente;
import com.backend.backendtcc.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<ClienteDTO> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClienteDTO obterCliente(int idCliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        return clienteOptional.map(this::convertToDTO).orElse(null);
    }

    public ClienteDTO criarCliente(ClienteDTO clienteDTO) {
        Cliente cliente = convertToEntity(clienteDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);
        return convertToDTO(clienteSalvo);
    }

    public ClienteDTO atualizarCliente(int idCliente, ClienteDTO clienteDTO) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(idCliente);
        if (clienteOptional.isPresent()) {
            Cliente cliente = clienteOptional.get();
            BeanUtils.copyProperties(clienteDTO, cliente);
            Cliente clienteAtualizado = clienteRepository.save(cliente);
            return convertToDTO(clienteAtualizado);
        }
        return null;
    }

    public void deletarCliente(int idCliente) {
        clienteRepository.deleteById(idCliente);
    }

    private ClienteDTO convertToDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        BeanUtils.copyProperties(cliente, clienteDTO);
        return clienteDTO;
    }

    private Cliente convertToEntity(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        BeanUtils.copyProperties(clienteDTO, cliente);
        return cliente;
    }
}
