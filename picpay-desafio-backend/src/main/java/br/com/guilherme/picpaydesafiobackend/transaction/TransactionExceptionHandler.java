package br.com.guilherme.picpaydesafiobackend.transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe para manipular exceções relacionadas a transações.
 */
@ControllerAdvice
public class TransactionExceptionHandler {

    /**
     * Manipula a exceção InvalidTransactionException.
     *
     * @param exception A exceção InvalidTransactionException a ser tratada.
     * @return Uma resposta HTTP com status de erro 400 (Bad Request) e a mensagem de erro da exceção.
     */
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Object> handle(InvalidTransactionException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
