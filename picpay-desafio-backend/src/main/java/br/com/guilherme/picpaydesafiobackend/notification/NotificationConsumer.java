package br.com.guilherme.picpaydesafiobackend.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.guilherme.picpaydesafiobackend.transaction.Transaction;

/**
 * Consumidor de notificações responsável por ouvir mensagens de um tópico Kafka e enviar notificações.
 */
@Service
public class NotificationConsumer {
    // Logger para registro de logs
    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationConsumer.class);
    // RestClient para fazer requisições HTTP
    private RestClient restClient;

    /**
     * Construtor do NotificationConsumer com um RestClient.
     *
     * @param builder O construtor do RestClient usado para criar a instância do RestClient.
     */
    public NotificationConsumer(RestClient.Builder builder) {
        this.restClient = builder.baseUrl(
                "https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
                .build();
    }

    /**
     * Método para receber e processar notificações de transação.
     *
     * @param transaction A transação para a qual a notificação está sendo enviada.
     */
    @SuppressWarnings("null")
    @KafkaListener(topics = "transaction-notification", groupId = "picpay-desafio-backend")
    public void receiveNotification(Transaction transaction) {
        LOGGER.info("Notificando transação {}...", transaction);

        var response = restClient.get().retrieve().toEntity(Notification.class);

        if (response.getStatusCode().isError() || !response.getBody().message())
            throw new NotificationException("Erro ao notificar transação " + transaction);

        LOGGER.info("Notificação enviada com sucesso {}...", response.getBody());
    }
}
