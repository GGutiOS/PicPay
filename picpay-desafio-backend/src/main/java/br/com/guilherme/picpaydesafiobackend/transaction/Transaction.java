package br.com.guilherme.picpaydesafiobackend.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Registro que representa uma transação.
 */
@Table("TRANSACTIONS")
public record Transaction(
        /**
         * Identificador único da transação.
         */
        @Id Long id,
        /**
         * Identificador do pagador da transação.
         */
        Long payer,
        /**
         * Identificador do recebedor da transação.
         */
        Long payee,
        /**
         * Valor da transação.
         */
        BigDecimal value,
        /**
         * Data e hora de criação da transação.
         */
        @CreatedDate LocalDateTime createdAt) {

    /**
     * Construtor da transação.
     *
     * @param id        O identificador único da transação.
     * @param payer     O identificador do pagador da transação.
     * @param payee     O identificador do recebedor da transação.
     * @param value     O valor da transação.
     * @param createdAt A data e hora de criação da transação.
     */
    public static Transaction createTransaction(Long id, Long payer, Long payee, BigDecimal value, LocalDateTime createdAt) {
        return new Transaction(id, payer, payee, value.setScale(2), createdAt);
    }

}
