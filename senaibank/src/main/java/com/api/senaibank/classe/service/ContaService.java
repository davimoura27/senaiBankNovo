package com.api.senaibank.classe.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.senaibank.classe.Conta;
import com.api.senaibank.classe.Transacao;
import com.api.senaibank.classe.repository.ContaRepository;


@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    ContaService(ContaRepository contaRepository){
        this.contaRepository = contaRepository;
    }

    public Conta create(Conta conta){
        return contaRepository.save(conta);

    }
    public List<Conta> getAll(){
        return contaRepository.findAll();
    }
    public Conta getByid(Long id){
        return contaRepository.findById(id).orElse(null);

    }
    public Conta update(Conta contaexistente, Conta contanova){
        contaexistente.setSaldo(contanova.getSaldo());
        return contaRepository.save(contaexistente);
       
    }
    public Conta delete(Long id){
      Conta conta = getByid(id);
      contaRepository.delete(conta);
      return conta;
    }
    public boolean temSaldo(Transacao transacao){
        Conta conta = getByid(transacao.getContaOrigem().getId());
        boolean temSaldo = (
            conta.getSaldo() >= transacao.getValor()
        );
        return true;
    }


}
