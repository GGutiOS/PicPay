package br.com.guilherme.picpaydesafiobackend.autorization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.com.guilherme.picpaydesafiobackend.transaction.Transaction;
import br.com.guilherme.picpaydesafiobackend.transaction.UnauthorizedTransactionException;

/**
 * Service responsável por autorizar transações.
 */
@Service
public class AuthorizerService {
    // Logger para registro de logs
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizerService.class);
    // RestClient para fazer requisições HTTP
    private RestClient restClient;

    /**
     * Construtor do AuthorizerService com um RestClient.
     *
     * @param builder O construtor do RestClient usado para criar a instância do RestClient.
     */
    public AuthorizerService(RestClient.Builder builder) {
        this.restClient = builder.baseUrl(
                "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc").build();
    }

    /**
     * Autoriza uma transação.
     *
     * @param transaction A transação a ser autorizada.
     * @throws UnauthorizedTransactionException Se a transação não for autorizada.
     */
    @SuppressWarnings("null")
    public void authorize(Transaction transaction) {
        LOGGER.info("Autorizando transação {}...", transaction);

        var response = restClient.get().retrieve().toEntity(Authorization.class);
        if (response.getStatusCode().isError() || !response.getBody().isAuthorized())
            throw new UnauthorizedTransactionException("Não autorizado!");
    }
}

/**
 * Registro de Autorização para representar a resposta de autorização.
 */
record Authorization(String message) {
    /**
     * Verifica se a autorização foi concedida.
     *
     * @return Verdadeiro se autorizado, falso caso contrário.
     */
    public boolean isAuthorized() {
        return message.equals("Autorizado");
    }
}
