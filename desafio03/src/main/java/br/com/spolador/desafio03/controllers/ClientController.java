package br.com.spolador.desafio03.controllers;

import br.com.spolador.desafio03.dto.ClientDto;
import br.com.spolador.desafio03.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id){
        ClientDto dto = clientService.findById(id);
        return ResponseEntity.ok(dto); // retorna 200 em caso de sucesso na busca
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable){
        Page<ClientDto> dto = clientService.fildAll(pageable);
        return ResponseEntity.ok(dto); // retorna 200 em caso de sucesso na busca
    }

    @PostMapping
    public ResponseEntity<ClientDto> insert (@Valid @RequestBody ClientDto dto){
        dto = clientService.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto); // retorna 201 em caso de sucesso na inserção
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDto> update(@PathVariable Long id, @Valid @RequestBody ClientDto dto){
        dto = clientService.update(id, dto);
        return ResponseEntity.ok(dto); //retorna 200 em caso de sucesso ao atualizar
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        clientService.deleteById(id);
        return ResponseEntity.noContent().build(); // retorna 204 em caso de sucesso ao deletar
    }
}
