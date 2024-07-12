package com.devsuperior.services;

import com.devsuperior.dto.ClientDTO;
import com.devsuperior.entities.Client;
import com.devsuperior.repositories.ClientRepository;
import com.devsuperior.services.exceptions.DatabaseException;
import com.devsuperior.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <h1> DEVSUPERIOR - Java Spring Professional - Capítulo: API REST, camadas, CRUD, exceções,
 * validações</h1>
 * DESAFIO: CRUD de clientes
 * <p>
 * <b>Note:</b> Desenvolvido na linguagem Java.
 *
 * @author  Marcos Ferreira Shirafuchi
 * @version 1.0
 * @since   12/07/2024
 */

//Pode colocar @Component ou service
@Service
public class ClientService {

    //Dependencia do repositoy
    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
        entity =repository.save(entity);
        ClientDTO clientDTO = new ClientDTO(entity);
        return clientDTO;
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        //Esse findById faz a consulta SQL no banco de dados

        Client client = repository.findById(id).orElseThrow(
                //Aqui você está lançando a própria exceção
                ()-> new ResourceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);

    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try{
            //Vai estanciar pelo id do banco de dados
            Client entity = repository.getReferenceById(id);
            copyDTOToEntity(dto, entity);
            return new ClientDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado");
        }

    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            //Esse findById faz a consulta SQL no banco de dados
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }

    private void copyDTOToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }
}
