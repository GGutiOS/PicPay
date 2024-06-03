package br.com.guilherme.picpaydesafiobackend.transaction;

/**
 * Exceção lançada quando ocorre uma transação inválida.
 */
public class InvalidTransactionException extends RuntimeException {

    /**
     * Construtor que aceita uma mensagem de erro.
     *
     * @param message A mensagem de erro associada à exceção.
     */
    public InvalidTransactionException(String message) {
        super(message);
    }
}
