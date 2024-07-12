package com.devsuperior.controllers;

import com.devsuperior.dto.ClientDTO;
import com.devsuperior.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

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


@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    //Colocar o Autowired para injetar
    @Autowired
    //Injetar um componente do ClientService
    private ClientService service;

    //Método Get
    //Pageable faz a busca pagina dos registros
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable pageable){
        Page<ClientDTO> dto = service.findAll(pageable);
        return ResponseEntity.ok(dto);
    }

    //Método Post
    @PostMapping
    //RequestBody vai receber os dados da Request do postman
    public ResponseEntity<ClientDTO>  insert(@Valid @RequestBody ClientDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    //Método Get
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO>  findById(@PathVariable Long id){
        ClientDTO dto = service.findById(id);
        //Estamos custamizando a respota
        return ResponseEntity.ok(dto);
    }

    //Método Put
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id,@Valid @RequestBody ClientDTO dto){
        dto = service.update(id,dto);
        //Estamos custamizando a respota
        return ResponseEntity.ok(dto);
    }

    //Método Delete
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        //Código 204 é quando a respota deu certo e não corpo
        return ResponseEntity.noContent().build();
    }
}
