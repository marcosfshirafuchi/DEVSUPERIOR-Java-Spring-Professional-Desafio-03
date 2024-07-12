package com.devsuperior.repositories;

import com.devsuperior.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

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

public interface ClientRepository extends JpaRepository<Client,Long> {
}
