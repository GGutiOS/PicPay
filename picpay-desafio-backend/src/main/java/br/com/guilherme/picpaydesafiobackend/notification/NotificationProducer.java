package br.com.guilherme.picpaydesafiobackend.notification;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.guilherme.picpaydesafiobackend.transaction.Transaction;

/**
 * Produtor de notificações responsável por enviar transações para um tópico Kafka.
 */
@Service
public class NotificationProducer {
    // Template do Kafka para enviar mensagens
    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    /**
     * Construtor do NotificationProducer.
     *
     * @param kafkaTemplate O KafkaTemplate usado para enviar mensagens para o Kafka.
     */
    public NotificationProducer(KafkaTemplate<String, Transaction> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Método para enviar uma notificação de transação para o Kafka.
     *
     * @param transaction A transação a ser enviada como notificação.
     */
    public void sendNotification(Transaction transaction) {
        kafkaTemplate.send("transaction-notification", transaction);
    }
}
