package com.backend.backendtcc.controller;

import com.backend.backendtcc.dto.ClienteDTO;
import com.backend.backendtcc.dto.SubCategoriaDTO;
import com.backend.backendtcc.service.ClienteService;
import com.backend.backendtcc.service.SubCategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;
    private final SubCategoriaService subCategoriaService;

    public ClienteController(ClienteService clienteService, SubCategoriaService subCategoriaService) {
        this.clienteService = clienteService;
        this.subCategoriaService = subCategoriaService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarClientes() {
        List<ClienteDTO> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> obterCliente(@PathVariable int idCliente) {
        ClienteDTO cliente = clienteService.obterCliente(idCliente);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO novoCliente = clienteService.criarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<ClienteDTO> atualizarCliente(@PathVariable int idCliente, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteAtualizado = clienteService.atualizarCliente(idCliente, clienteDTO);
        if (clienteAtualizado != null) {
            return ResponseEntity.ok(clienteAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{idCliente}")
    public ResponseEntity<Void> deletarCliente(@PathVariable int idCliente) {
        clienteService.deletarCliente(idCliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categorias/{idCategoria}/subcategorias")
    public List<SubCategoriaDTO> listarSubCategoriasPorCategoria(@PathVariable int idCategoria) {
        return subCategoriaService.listarSubCategoriasPorCategoria(idCategoria);
    }

}
