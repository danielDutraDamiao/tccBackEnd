package com.backend.backendtcc.service;

import com.backend.backendtcc.dto.CategoriaDTO;
import com.backend.backendtcc.dto.ProdutoDTO;
import com.backend.backendtcc.model.Categoria;
import com.backend.backendtcc.model.Produto;
import com.backend.backendtcc.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }



    public ProdutoDTO obterProduto(int idProduto) {
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        return produtoOptional.map(this::convertToDTO).orElse(null);
    }

    public ProdutoDTO criarProduto(ProdutoDTO produtoDTO) {
        Produto produto = convertToEntity(produtoDTO);

        // Obtenha o ID da categoria do DTO
        int idCategoria = produtoDTO.getCategoriaProduto().getIdCategoria();

        // Crie uma instância de Categoria com o ID
        Categoria categoria = new Categoria();
        categoria.setIdCategoria(idCategoria);

        // Associe a categoria ao produto
        produto.setCategoria(categoria);

        // Salve o produto no banco de dados
        Produto produtoSalvo = produtoRepository.save(produto);

        // Converta o produto salvo para DTO
        return convertToDTO(produtoSalvo);
    }


    public ProdutoDTO atualizarProduto(int idProduto, ProdutoDTO produtoDTO) {
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        if (produtoOptional.isPresent()) {
            Produto produto = produtoOptional.get();
            BeanUtils.copyProperties(produtoDTO, produto);
            Produto produtoAtualizado = produtoRepository.save(produto);
            return convertToDTO(produtoAtualizado);
        }
        return null;
    }

    public void deletarProduto(int idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    private ProdutoDTO convertToDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();
        produtoDTO.setIdProduto(produto.getIdProduto());
        produtoDTO.setNomeProduto(produto.getNomeProduto());
        produtoDTO.setPrecoProduto(produto.getPrecoProduto());
        produtoDTO.setImagemProduto(produto.getImagemProduto());

        Categoria categoria = produto.getCategoria();
        if (categoria != null) {
            CategoriaDTO categoriaDTO = new CategoriaDTO();
            categoriaDTO.setIdCategoria(categoria.getIdCategoria());
            categoriaDTO.setNomeCategoria(categoria.getNomeCategoria());
            produtoDTO.setCategoriaProduto(categoriaDTO);
        }

        return produtoDTO;
    }


    private Produto convertToEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(produtoDTO, produto);
        return produto;
    }
}
