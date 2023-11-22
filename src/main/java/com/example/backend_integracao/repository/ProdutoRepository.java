package com.example.backend_integracao.repository;

import com.example.backend_integracao.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Interface do Spring Data JPA para realizar operações CRUD no banco de dados
}
