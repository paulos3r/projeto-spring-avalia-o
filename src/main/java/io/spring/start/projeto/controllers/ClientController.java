package io.spring.start.projeto.controllers;

import io.spring.start.projeto.dtos.ClientDto;
import io.spring.start.projeto.model.ClientModel;
import io.spring.start.projeto.services.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/cliente")
public class ClientController {

                //sett construtor
    final ClientService clientService;

    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }
                //restfull-api

                //vou receber um objeto cliente já tratado todos os campos, arquivo vem em json
    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDto clientDto) {

                    //verificar se existe nome e email do cliente
        if(clientService.existsByNameAndEmail(clientDto.getName(), clientDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: already exists name or email.");
        }

                    //INSTANCIA
        var clientModel = new ClientModel();
                    //CONVERSAO DO DTO EM MODEL
        BeanUtils.copyProperties(clientDto, clientModel);
                    //INSTANCIA DA DATA SET DATA
        //clientModel.setBirth_date(LocalDateTime.now(ZoneId.of("UTC")));
                    //STATUS 201 / SALVANDO NO BANCO
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(clientModel));
    }

    @GetMapping
    public ResponseEntity<List<ClientModel>> getClient(){
                    //RETORNA A LISTA DE CADASTRO
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id){
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
                    //preciso saber se existe cliente!
        if (!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: Client not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(clientModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id){
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if(!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: Client not found. check and id");
        }
        clientService.delete(clientModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("DELETE: Client deleted success.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id,
                                               @RequestBody @Valid ClientDto clientDto){
        Optional<ClientModel> clientModelOptional = clientService.findById(id);
        if (!clientModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND: Client not found");
        }
        var clientModel = clientModelOptional.get();
                    //se eu esquecer alguma varavel gera um erro 500 server: estudar nova forma de fazer isso
        clientModel.setName(clientDto.getName());
        clientModel.setEmail(clientDto.getEmail());
        clientModel.setBirth_date(clientDto.getBirth_date());

        return ResponseEntity.status(HttpStatus.OK).body(clientService.update(clientModel));
    }
}