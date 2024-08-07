package com.api.senaibank.classe.controller;

import com.api.senaibank.classe.Conta;

import com.api.senaibank.classe.service.ContaService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.create(conta));
    }

    @GetMapping
    public ResponseEntity<List<Conta>> getAll() {

        return ResponseEntity.ok(contaService.getAll());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getById(@PathVariable Long id) {

        return ResponseEntity.ok(contaService.getByid(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> update(@PathVariable Long id, @RequestBody Conta contaAtualizada) {
        Conta contaExistente = contaService.getByid(id);
        if (contaExistente == null) {
            return ResponseEntity.notFound().build();
        }
        contaExistente.setSaldo(contaAtualizada.getSaldo());
        return ResponseEntity.ok(contaService.update(contaExistente, contaAtualizada));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable Long id) {
       
        contaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
