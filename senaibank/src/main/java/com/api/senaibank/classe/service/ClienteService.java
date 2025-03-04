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
 
    @Autowired
    private EmailService emailService;

    public Cliente create(Cliente cliente) {
      Cliente clienteNovo = clienteRepository.save(cliente);
      sendEmailNotification(clienteNovo);
      return clienteNovo;
    }
    private void sendEmailNotification(Cliente cliente){
        String subject = "Bem vindo!";
        String texto = "Você foi cadastrado com sucesso" + " " + cliente.getNome();
        emailService.sendEmail(cliente.getEmail(), subject, texto);
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
    public List<Cliente> getAllAtivos(){
        return clienteRepository.findByAtivoTrue();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteSalvo, Cliente clienteNovo) {

        if (clienteNovo.getNome() != null) {
            clienteSalvo.setNome(clienteNovo.getNome());
        }
        if (clienteNovo.getCpf() != null) {
            clienteSalvo.setCpf(clienteNovo.getCpf());
        }
        if (clienteNovo.getTelefone() != null) {
            clienteSalvo.setTelefone(clienteNovo.getTelefone());
        }
        if (clienteNovo.getDatanascimento() != null) {
            clienteSalvo.setDatanascimento(clienteNovo.getDatanascimento());
        }
        if (clienteNovo.getEndereco() != null) {
            clienteSalvo.setEndereco(clienteNovo.getEndereco());
        }
        if (clienteNovo.getEmail() != null) {
            clienteSalvo.setEmail(clienteNovo.getEmail());  
        }
        if (clienteNovo.isAtivo() == false) {
            clienteSalvo.setAtivo(false);
        }

        return clienteRepository.save(clienteSalvo);
    }

    public Cliente delete(Long id) {
        
          clienteRepository.deleteById(id);

        Cliente cliente = getById(id);

       
        cliente.setAtivo(false);

        return clienteRepository.save(cliente);

    }
}
