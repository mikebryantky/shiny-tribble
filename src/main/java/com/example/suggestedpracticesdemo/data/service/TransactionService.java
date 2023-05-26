package com.example.suggestedpracticesdemo.data.service;

import com.example.suggestedpracticesdemo.data.dto.TransactionDto;
import com.example.suggestedpracticesdemo.data.model.Transaction;
import com.example.suggestedpracticesdemo.data.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<TransactionDto> getAll() {
        return transactionRepository.findAll()
                .stream()
                .map(TransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    public TransactionDto get(String id) {
        return TransactionDto.fromEntity(
                transactionRepository.findById(id)
                        .orElseThrow(
                                () -> new EntityNotFoundException("No transaction found with id " + id))
        );
    }

    public TransactionDto save(TransactionDto transactionDto) {
        Transaction entity = TransactionDto.toEntity(transactionDto);
        entity = transactionRepository.save(entity);
        TransactionDto dto = TransactionDto.fromEntity(entity);
        return dto;

//        return TransactionDto.fromEntity(
//                transactionRepository.save(TransactionDto.toEntity(transactionDto))
//        );
    }
}
