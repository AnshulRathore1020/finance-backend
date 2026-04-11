package com.zorvyn.finance_backend.service;

import com.zorvyn.finance_backend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private TransactionRepository transactionRepository;

    // Summary
    public Map<String, Object> getSummary() {
        BigDecimal totalIncome = transactionRepository.getTotalIncome();
        BigDecimal totalExpense = transactionRepository.getTotalExpense();
        BigDecimal netBalance = totalIncome.subtract(totalExpense);

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalIncome", totalIncome);
        summary.put("totalExpense", totalExpense);
        summary.put("netBalance", netBalance);

        return summary;
    }

    // Category wise totals
    public Map<String, Object> getCategoryWiseTotals() {
        List<Object[]> results = transactionRepository.getCategoryWiseTotals();

        Map<String, Object> categoryTotals = new HashMap<>();
        for (Object[] row : results) {
            categoryTotals.put((String) row[0], row[1]);
        }

        return categoryTotals;
    }

    // Monthly trends
    public List<Object[]> getMonthlyTrends() {
        return transactionRepository.getMonthlyTrends();
    }

    // Recent activity
    public List<Object> getRecentActivity() {
        return transactionRepository.findTop5ByOrderByCreatedAtDesc()
                .stream()
                .map(t -> (Object) Map.of(
                        "id", t.getId(),
                        "amount", t.getAmount(),
                        "type", t.getType(),
                        "category", t.getCategory(),
                        "date", t.getDate()
                ))
                .collect(java.util.stream.Collectors.toList());
    }
}