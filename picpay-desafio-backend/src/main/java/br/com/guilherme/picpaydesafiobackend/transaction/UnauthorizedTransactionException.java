package br.com.guilherme.picpaydesafiobackend.transaction;

/**
 * Exceção lançada quando uma transação não é autorizada.
 */
public class UnauthorizedTransactionException extends RuntimeException {
    /**
     * Construtor que aceita uma mensagem de erro.
     *
     * @param message A mensagem de erro associada à exceção.
     */
    public UnauthorizedTransactionException(String message) {
        super(message);
    }
}
