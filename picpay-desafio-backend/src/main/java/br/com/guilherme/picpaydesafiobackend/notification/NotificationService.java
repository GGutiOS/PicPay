package br.com.guilherme.picpaydesafiobackend.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.com.guilherme.picpaydesafiobackend.transaction.Transaction;

/**
 * Serviço responsável por enviar notificações de transações.
 */
@Service
public class NotificationService {
    // Logger para registro de logs
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);
    // Produtor de notificações
    private NotificationProducer notificationProducer;

    /**
     * Construtor do NotificationService.
     *
     * @param notificationProducer O produtor de notificações usado para enviar notificações de transações.
     */
    public NotificationService(NotificationProducer notificationProducer) {
        this.notificationProducer = notificationProducer;
    }

    /**
     * Método para enviar uma notificação de transação.
     *
     * @param transaction A transação a ser notificada.
     */
    public void notify(Transaction transaction) {
        LOGGER.info("Notificando transação {}...", transaction);

        notificationProducer.sendNotification(transaction);
    }
}
