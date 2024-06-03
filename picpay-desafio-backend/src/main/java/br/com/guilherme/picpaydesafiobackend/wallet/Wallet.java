package br.com.guilherme.picpaydesafiobackend.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

/**
 * Registro que representa uma carteira.
 */
@Table("WALLETS")
public record Wallet(
        /**
         * Identificador único da carteira.
         */
        @Id Long id,
        /**
         * Nome completo associado à carteira.
         */
        String fullName,
        /**
         * CPF associado à carteira.
         */
        Long cpf,
        /**
         * E-mail associado à carteira.
         */
        String email,
        /**
         * Senha associada à carteira.
         */
        String password,
        /**
         * Tipo de carteira.
         */
        int type,
        /**
         * Saldo da carteira.
         */
        BigDecimal balance,
        /**
         * Versão da carteira.
         */
        @Version Long version) {

    /**
     * Método para debitar um valor da carteira.
     *
     * @param value O valor a ser debitado.
     * @return Uma nova carteira com o valor debitado.
     */
    public Wallet debit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type,
                balance.subtract(value), version);
    }

    /**
     * Método para creditar um valor na carteira.
     *
     * @param value O valor a ser creditado.
     * @return Uma nova carteira com o valor creditado.
     */
    public Wallet credit(BigDecimal value) {
        return new Wallet(id, fullName, cpf, email, password, type, balance.add(value), version);
    }
}
