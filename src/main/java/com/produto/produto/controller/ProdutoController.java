package com.produto.produto.controller;

import com.produto.produto.entity.ProdutoEntity;
import com.produto.produto.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ProdutoEntity criar(@RequestBody ProdutoEntity produto) {
        return service.salvar(produto);
    }

    @GetMapping
    public List<ProdutoEntity> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<ProdutoEntity> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}