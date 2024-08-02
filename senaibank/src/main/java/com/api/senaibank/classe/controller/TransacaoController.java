package com.api.senaibank.classe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.api.senaibank.classe.Transacao;
import com.api.senaibank.classe.service.TransacaoService;
import java.util.List;
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Transacao> create(@RequestBody Transacao transacao){
        return ResponseEntity.ok(transacaoService.create(transacao));
    }
    @GetMapping
    public ResponseEntity<List<Transacao>> getAll(){
        return ResponseEntity.ok(transacaoService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getByid(@PathVariable Long id){
        return ResponseEntity.ok(transacaoService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Transacao> update(@PathVariable Long id, @RequestBody Transacao transacao){
        return ResponseEntity.ok(transacaoService.update(id, transacao));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        transacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
