package br.com.guilherme.picpaydesafiobackend.notification;

/**
 * Registro que representa uma notificação.
 */
public record Notification(
        /**
         * O status da mensagem da notificação.
         */
        boolean message) {
}
