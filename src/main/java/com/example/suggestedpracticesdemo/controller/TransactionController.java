package com.example.suggestedpracticesdemo.controller;

import com.example.suggestedpracticesdemo.data.dto.TransactionDto;
import com.example.suggestedpracticesdemo.data.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Slf4j
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping(
            produces = "application/json"
    )
    public ResponseEntity<List<TransactionDto>> getAll() {
        return new ResponseEntity<>(transactionService.getAll(), OK);
    }

    @GetMapping(
            produces = "application/json",
            value = "/{id}"
    )
    public ResponseEntity<TransactionDto> getById(@PathVariable(name = "id") String id) {
        return new ResponseEntity<>(transactionService.get(id), OK);
    }

    @PostMapping(
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<TransactionDto> add(
            @RequestBody @Validated TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.save(transactionDto), OK);
    }

    @PutMapping(
            produces = "application/json",
            consumes = "application/json"
    )
    public ResponseEntity<TransactionDto> update(
            @RequestBody @Validated TransactionDto transactionDto) {
        return new ResponseEntity<>(transactionService.save(transactionDto), OK);
    }
}
