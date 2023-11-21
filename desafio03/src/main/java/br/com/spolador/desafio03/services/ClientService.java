package br.com.spolador.desafio03.services;

import br.com.spolador.desafio03.dto.ClientDto;
import br.com.spolador.desafio03.entities.Client;
import br.com.spolador.desafio03.repositories.ClientRepository;
import br.com.spolador.desafio03.services.exceptions.DatabaseExcetpion;
import br.com.spolador.desafio03.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDto findById(Long id){
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado")
        );
        return new ClientDto(client);
    }

    @Transactional
    public Page<ClientDto> fildAll(Pageable pageable){
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(ClientDto::new);
    }

    @Transactional
    public ClientDto insert(ClientDto dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = clientRepository.save(entity);
        return new ClientDto(entity);
    }

    @Transactional
    public ClientDto update(Long id, ClientDto dto){
        try{
            Client entity = clientRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = clientRepository.save(entity);
            return new ClientDto(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteById(Long id){
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            clientRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseExcetpion("Falha de integridade referencial");
        }
    }

    private void copyDtoToEntity(ClientDto dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
