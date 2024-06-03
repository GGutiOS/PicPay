package br.com.guilherme.picpaydesafiobackend.transaction;

import org.springframework.web.bind.annotation.*;
import io.swagger.oas.annotations.Operation;
import io.swagger.oas.annotations.Parameter;
import io.swagger.oas.annotations.media.Content;
import io.swagger.oas.annotations.media.Schema;
import io.swagger.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Operation(summary = "Criar uma nova transação", description = "Endpoint para criar uma nova transação.")
    @PostMapping
    @ApiResponse(responseCode = "201", description = "Transação criada", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Transaction.class))
    })
    public Transaction createTransaction(
            @Parameter(description = "Dados da nova transação", required = true) @RequestBody Transaction transaction) {
        return transactionService.create(transaction);
    }

    @Operation(summary = "Listar todas as transações", description = "Endpoint para listar todas as transações.")
    @GetMapping
    public List<Transaction> list() {
        return transactionService.list();
    }
}
