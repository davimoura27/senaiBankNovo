package com.api.senaibank.classe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.senaibank.classe.Endereco;
import com.api.senaibank.classe.repository.EnderecoRepository;
import com.google.gson.Gson;

@Service
public class EnderecoService {

    @Autowired
    EnderecoRepository enderecoRepository;
    @Autowired
    ViaCepService viaCepService;

    public List<Endereco> getAll() {
        return enderecoRepository.findAll();
    }

    public Endereco getById(Long id) {
        return enderecoRepository.findById(id).orElse(null);
    }

    public Endereco getEnderecoByCep(String cep) {
        System.out.println("Buscando CEP:" + cep);
        Endereco endereco = viaCepService.getEnderecoByCep(cep);

        endereco.setCep(endereco.getCep().replace("-", ""));

        Gson gson = new Gson();
        System.out.println("Endereco:" + endereco.toString());
        return enderecoRepository.save(endereco);

    }

    public Endereco create(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco update(Endereco endereco, Long id) {
        endereco.setId(id);
        return enderecoRepository.save(endereco);
    }

    public Endereco delete(Long id) {
        Endereco endereco = getById(id);
        enderecoRepository.delete(endereco);
        return endereco;
    }

}
