package com.zorvyn.finance_backend.service;

import com.zorvyn.finance_backend.dto.TransactionRequest;
import com.zorvyn.finance_backend.dto.TransactionResponse;
import com.zorvyn.finance_backend.model.Transaction;
import com.zorvyn.finance_backend.model.Transaction.TransactionType;
import com.zorvyn.finance_backend.model.User;
import com.zorvyn.finance_backend.repository.TransactionRepository;
import com.zorvyn.finance_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    // Transaction banao
    public TransactionResponse create(TransactionRequest request, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(request.getCategory());
        transaction.setDate(request.getDate());
        transaction.setDescription(request.getDescription());
        transaction.setUser(user);

        Transaction saved = transactionRepository.save(transaction);
        return mapToResponse(saved);
    }

    // Sab transactions — filter ke saath
    public List<TransactionResponse> getAll(String type, String category,
                                             String startDate, String endDate) {
        List<Transaction> transactions;

        if (type != null && category != null) {
            transactions = transactionRepository.findByTypeAndCategory(
                    TransactionType.valueOf(type.toUpperCase()), category);

        } else if (type != null) {
            transactions = transactionRepository.findByType(
                    TransactionType.valueOf(type.toUpperCase()));

        } else if (category != null) {
            transactions = transactionRepository.findByCategory(category);

        } else if (startDate != null && endDate != null) {
            transactions = transactionRepository.findByDateBetween(
                    LocalDate.parse(startDate), LocalDate.parse(endDate));

        } else {
            transactions = transactionRepository.findAll();
        }

        return transactions.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Transaction update karo
    public TransactionResponse update(Long id, TransactionRequest request) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transaction.setCategory(request.getCategory());
        transaction.setDate(request.getDate());
        transaction.setDescription(request.getDescription());

        Transaction updated = transactionRepository.save(transaction);
        return mapToResponse(updated);
    }

    // Transaction delete karo
    public void delete(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found");
        }
        transactionRepository.deleteById(id);
    }

    // Entity to DTO
    private TransactionResponse mapToResponse(Transaction t) {
        TransactionResponse response = new TransactionResponse();
        response.setId(t.getId());
        response.setAmount(t.getAmount());
        response.setType(t.getType());
        response.setCategory(t.getCategory());
        response.setDate(t.getDate());
        response.setDescription(t.getDescription());
        response.setUserName(t.getUser().getName());
        response.setCreatedAt(t.getCreatedAt());
        return response;
    }
}