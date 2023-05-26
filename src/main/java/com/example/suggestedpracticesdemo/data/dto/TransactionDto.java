package com.example.suggestedpracticesdemo.data.dto;

import com.example.suggestedpracticesdemo.data.model.Transaction;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;


@Data
public class TransactionDto implements Serializable {

    String id;

    @NotEmpty
    @Size(min = 5,
            max = 50,
            message = "Account Number should have at least five and no more than 50 characters")
    String accountNumber;

    @NotNull(message = "Amount is required.")
    BigDecimal transactionAmount;

    @NotNull(message = "Transaction Date is required.")
    Instant transactionDate;

    Instant createDate;

    public static TransactionDto fromEntity(Transaction transactionEntity) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transactionEntity.getId());
        transactionDto.setAccountNumber(transactionEntity.getAccountNumber());
        transactionDto.setTransactionAmount(transactionEntity.getTransactionAmount());
        transactionDto.setTransactionDate(transactionEntity.getTransactionDate());
        transactionDto.setCreateDate(transactionEntity.getCreateDate());

        return transactionDto;
    }

    public static Transaction toEntity(TransactionDto transactionDto) {
        Transaction transactionEntity = new Transaction();
        transactionEntity.setId(
                transactionDto.getId() != null ?
                        transactionDto.getId() :
                        UUID.randomUUID().toString());

        transactionEntity.setCreateDate(
                transactionDto.getCreateDate() != null ?
                        transactionDto.getCreateDate() :
                        Instant.now());

        transactionEntity.setAccountNumber(transactionDto.getAccountNumber());
        transactionEntity.setTransactionAmount(transactionDto.getTransactionAmount());
        transactionEntity.setTransactionDate(transactionDto.getTransactionDate());

        return transactionEntity;
    }
}