package br.com.guilherme.picpaydesafiobackend.wallet;

import org.springframework.data.repository.CrudRepository;

/**
 * Interface de repositório para carteiras.
 */
public interface WalletRepository extends CrudRepository<Wallet, Long> {

}
