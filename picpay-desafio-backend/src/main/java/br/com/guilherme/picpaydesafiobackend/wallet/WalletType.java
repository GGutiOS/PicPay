package br.com.guilherme.picpaydesafiobackend.wallet;

/**
 * Enumeração que representa os tipos de carteira.
 */
public enum WalletType {
    /**
     * Tipo de carteira comum.
     */
    COMUM(1),
    /**
     * Tipo de carteira para lojistas.
     */
    LOJISTA(2);

    // Valor associado a cada tipo de carteira
    private int value;

    /**
     * Construtor privado para WalletType.
     *
     * @param value O valor associado ao tipo de carteira.
     */
    private WalletType(int value) {
        this.value = value;
    }

    /**
     * Método para obter o valor associado ao tipo de carteira.
     *
     * @return O valor associado ao tipo de carteira.
     */
    public int getValue() {
        return value;
    }
}
