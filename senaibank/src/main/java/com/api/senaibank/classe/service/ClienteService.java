package com.api.senaibank.classe.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.api.senaibank.classe.Cliente;
import com.api.senaibank.classe.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }

    public Cliente getByid(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Cliente clienteAtualizar = getByid(id);
        if (clienteAtualizar == null) {
            return null;
        }

        clienteAtualizar.setNome(cliente.getNome());

        clienteAtualizar.setCpf(cliente.getCpf());

        clienteAtualizar.setTelefone(cliente.getTelefone());

        return clienteRepository.save(clienteAtualizar);
    }

    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
