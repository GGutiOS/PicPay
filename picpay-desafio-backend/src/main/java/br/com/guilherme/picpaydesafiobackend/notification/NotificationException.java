package br.com.guilherme.picpaydesafiobackend.notification;

/**
 * Exceção lançada quando ocorre um erro durante o processo de notificação.
 */
public class NotificationException extends RuntimeException {

    /**
     * Construtor que aceita uma mensagem de erro.
     *
     * @param message A mensagem de erro associada à exceção.
     */
    public NotificationException(String message){
        super(message);
    }
}
