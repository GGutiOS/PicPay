package br.com.guilherme.picpaydesafiobackend.transaction;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.guilherme.picpaydesafiobackend.autorization.AuthorizerService;
import br.com.guilherme.picpaydesafiobackend.notification.NotificationService;
import br.com.guilherme.picpaydesafiobackend.wallet.WalletRepository;
import br.com.guilherme.picpaydesafiobackend.wallet.WalletType;

/**
 * Serviço para manipular operações relacionadas a transações.
 */
@Service
public class TransactionService {
    // Logger para registro de logs
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);
    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;
    private final AuthorizerService authorizerService;
    private final NotificationService notificationService;

    /**
     * Construtor do TransactionService.
     *
     * @param transactionRepository O repositório de transações.
     * @param walletRepository      O repositório de carteiras.
     * @param authorizerService     O serviço de autorização de transações.
     * @param notificationService   O serviço de notificações.
     */
    public TransactionService(TransactionRepository transactionRepository,
                              WalletRepository walletRepository,
                              AuthorizerService authorizerService,
                              NotificationService notificationService) {
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
        this.authorizerService = authorizerService;
        this.notificationService = notificationService;
    }

    /**
     * Método para criar uma nova transação.
     *
     * @param transaction A transação a ser criada.
     * @return A transação criada.
     */
    @Transactional
    public Transaction create(Transaction transaction) {
        validate(transaction);

        var newTransaction = transactionRepository.save(transaction);

        var walletPayer = walletRepository.findById(transaction.payer()).get();
        var walletPayee = walletRepository.findById(transaction.payee()).get();
        walletRepository.save(walletPayer.debit(transaction.value()));
        walletRepository.save(walletPayee.credit(transaction.value()));

        authorizerService.authorize(transaction);
        notificationService.notify(newTransaction);

        return newTransaction;
    }

    /**
     * Método privado para validar uma transação.
     *
     * @param transaction A transação a ser validada.
     */
    /*
     * Uma transação é válida se:
     * - o pagador é uma carteira comum
     * - o pagador tem saldo suficiente
     * - o pagador não é o recebedor
     */
    private void validate(Transaction transaction) {
        LOGGER.info("Validando transação {}...", transaction);

        walletRepository.findById(transaction.payee())
                .map(payee -> walletRepository.findById(transaction.payer())
                        .map(
                                payer -> payer.type() == WalletType.COMUM.getValue() &&
                                        payer.balance().compareTo(transaction.value()) >= 0 &&
                                        !payer.id().equals(transaction.payee()) ? true : null)
                        .orElseThrow(() -> new InvalidTransactionException(
                                "Transação inválida - " + transaction)))
                .orElseThrow(() -> new InvalidTransactionException(
                        "Transação inválida - " + transaction));
    }

    /**
     * Método para listar todas as transações.
     *
     * @return Uma lista de transações.
     */
    public List<Transaction> list() {
        return transactionRepository.findAll();
    }
}
