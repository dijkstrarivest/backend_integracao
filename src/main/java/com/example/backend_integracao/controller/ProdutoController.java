package com.example.backend_integracao.controller;

import com.example.backend_integracao.model.Produto;
import com.example.backend_integracao.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<Page<Produto>> getAllProdutos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Produto> produtos = produtoRepository.findAll(pageable);
            return ResponseEntity.ok(produtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {
        try {
            Optional<Produto> produto = produtoRepository.findById(id);
            return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Produto> createProduto(@RequestBody Produto produto) {
        try {
            Produto savedProduto = produtoRepository.save(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProduto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProduto(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            if (!produtoRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            produto.setId(id);
            Produto updatedProduto = produtoRepository.save(produto);
            return ResponseEntity.ok(updatedProduto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable Long id) {
        try {
            if (!produtoRepository.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            produtoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}