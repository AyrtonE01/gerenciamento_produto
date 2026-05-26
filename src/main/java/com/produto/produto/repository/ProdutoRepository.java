package com.produto.produto.repository;

import com.produto.produto.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    static UserDetails findByLogin(String role);
}