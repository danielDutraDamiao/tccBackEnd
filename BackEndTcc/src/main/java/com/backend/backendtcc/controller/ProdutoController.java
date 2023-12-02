package com.backend.backendtcc.controller;

import com.backend.backendtcc.dto.ProdutoDTO;
import com.backend.backendtcc.service.ProdutoService;
import com.backend.backendtcc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private UserService userService;
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        List<ProdutoDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> obterProduto(@PathVariable int idProduto) {
        ProdutoDTO produto = produtoService.obterProduto(idProduto);
        if (produto != null) {
            return ResponseEntity.ok(produto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO novoProduto = produtoService.criarProduto(produtoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @PutMapping("/{idProduto}")
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable int idProduto, @RequestBody ProdutoDTO produtoDTO) {
        ProdutoDTO produtoAtualizado = produtoService.atualizarProduto(idProduto, produtoDTO);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/comprar/{idProduto}/{idUsuario}")
    public ResponseEntity<?> comprarProduto(@PathVariable int idProduto, @PathVariable Long idUsuario, @RequestParam("quantidade") int quantidade, @RequestParam("ecopointsUsados") BigDecimal ecopointsUsados) {
        ProdutoDTO produto = produtoService.obterProduto(idProduto);
        if (produto == null || produto.getQuantidade() < quantidade) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto indisponível ou quantidade insuficiente em estoque.");
        }

        BigDecimal precoProduto = BigDecimal.valueOf(produto.getPrecoProduto());
        BigDecimal custoTotal = precoProduto.multiply(BigDecimal.valueOf(quantidade));

        // Obter os EcoPoints do usuário
        BigDecimal ecopointsDoUsuario = userService.obterEcoPointsDoUsuario(idUsuario);

        // Calcular o desconto máximo possível
        BigDecimal descontoMaximo = ecopointsDoUsuario.multiply(new BigDecimal("0.5"));

        // Calcular o desconto real com base nos EcoPoints usados
        BigDecimal descontoReal = ecopointsUsados.min(descontoMaximo).multiply(new BigDecimal("0.5"));

        // Aplicar desconto no custo total
        BigDecimal custoTotalComDesconto = custoTotal.subtract(descontoReal);

        // Verificar se o usuário tem EcoCoins suficientes após o desconto
        if (!userService.atualizarEcoCoinsUsuario(idUsuario, custoTotalComDesconto.negate())) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("EcoCoins insuficientes para realizar a compra.");
        }

        if (!produtoService.diminuirQuantidadeProduto(idProduto, quantidade)) {
            // Reverter a atualização dos ecocoins
            userService.atualizarEcoCoinsUsuario(idUsuario, custoTotalComDesconto);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao atualizar o estoque do produto.");
        }

        // Atualizar os EcoPoints do usuário
        userService.atualizarEcoPointsDoUsuario(idUsuario, ecopointsUsados.negate());

        return ResponseEntity.ok().build();
    }








    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable int idProduto) {
        produtoService.deletarProduto(idProduto);
        return ResponseEntity.noContent().build();
    }
}
