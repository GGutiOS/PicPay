package br.com.guilherme.picpaydesafiobackend.transaction;

import org.springframework.data.repository.ListCrudRepository;

/**
 * Interface de repositório para transações.
 */
public interface TransactionRepository extends ListCrudRepository<Transaction, Long> {

}
