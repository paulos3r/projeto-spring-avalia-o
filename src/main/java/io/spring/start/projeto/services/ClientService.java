package io.spring.start.projeto.services;

import io.spring.start.projeto.model.ClientModel;
import io.spring.start.projeto.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    public ClientRepository clientRepository;

    /*final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }*/
    @Transactional
    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    public boolean existsByNameAndEmail(String name, String email){
        return clientRepository.existsByNameAndEmail(name,email);
    }

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public Optional<ClientModel> findById(UUID id) {
        return clientRepository.findById(id);
    }

    @Transactional
    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }

    public Object update(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }
}
