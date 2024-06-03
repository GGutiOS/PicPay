package br.com.guilherme.picpaydesafiobackend;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Classe principal da aplicação PicpayDesafioBackend.
 */
@EnableJdbcAuditing
@SpringBootApplication
public class PicpayDesafioBackendApplication {

    /**
     * Método principal para iniciar a aplicação Spring Boot.
     *
     * @param args Os argumentos da linha de comando.
     */
    public static void main(String[] args) {
        SpringApplication.run(PicpayDesafioBackendApplication.class, args);
    }

    /**
     * Método para criar o tópico de notificação.
     *
     * @return O tópico de notificação criado.
     */
    @Bean
    NewTopic notificationTopic() {
        return TopicBuilder.name("transaction-notification")
                .build();
    }
}
