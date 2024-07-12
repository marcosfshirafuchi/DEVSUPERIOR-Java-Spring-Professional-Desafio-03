package com.devsuperior.services.exceptions;

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
//RuntimeException não exige try catch
public class DatabaseException extends RuntimeException{
    public DatabaseException(String msg){
        super(msg);
    }
}
