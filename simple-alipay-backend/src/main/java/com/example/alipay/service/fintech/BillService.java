package com.example.alipay.service.fintech;

import com.example.alipay.model.fintech.Bill;
import com.example.alipay.repository.fintech.BillRepository;
import com.example.alipay.repository.fintech.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BillService {
    private final BillRepository billRepository;
    private final UserRepository userRepository;

    public BillService(BillRepository billRepository, UserRepository userRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
    }

    public List<Bill> getUserBills(Long userId) {
        return billRepository.findByUserIdOrderByTransactionTimeDesc(userId);
    }

    public List<Bill> getUserBillsByType(Long userId, String type) {
        return billRepository.findByUserIdAndTypeOrderByTransactionTimeDesc(userId, type);
    }

    public BillOverviewDto getBillOverview(Long userId) {
        List<Bill> bills = getUserBills(userId);
        
        BigDecimal totalIncome = bills.stream()
                .filter(bill -> "INCOME".equals(bill.getType()))
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        BigDecimal totalExpenditure = bills.stream()
                .filter(bill -> "EXPENDITURE".equals(bill.getType()))
                .map(Bill::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        Map<String, BigDecimal> categoryExpenditure = bills.stream()
                .filter(bill -> "EXPENDITURE".equals(bill.getType()))
                .collect(Collectors.groupingBy(
                    Bill::getCategory,
                    Collectors.reducing(BigDecimal.ZERO, Bill::getAmount, BigDecimal::add)
                ));

        BillOverviewDto overview = new BillOverviewDto();
        overview.setTotalIncome(totalIncome);
        overview.setTotalExpenditure(totalExpenditure);
        overview.setCategoryExpenditure(categoryExpenditure);
        overview.setBillCount(bills.size());
        
        return overview;
    }

    public Bill createBill(Long userId, BigDecimal amount, String type, String category, String remark) {
        Bill bill = new Bill();
        bill.setUserId(userId);
        bill.setAmount(amount);
        bill.setType(type);
        bill.setCategory(category);
        bill.setTransactionTime(LocalDateTime.now());
        bill.setRemark(remark);
        bill = billRepository.save(bill);

        userRepository.findById(userId).ifPresent(u -> {
            BigDecimal absAmt = amount == null ? BigDecimal.ZERO : amount.abs();
            BigDecimal current = u.getBalance() == null ? BigDecimal.ZERO : u.getBalance();
            if ("INCOME".equalsIgnoreCase(type)) {
                u.setBalance(current.add(absAmt));
            } else if ("EXPENDITURE".equalsIgnoreCase(type)) {
                u.setBalance(current.subtract(absAmt));
            }
            userRepository.save(u);
        });

        return bill;
    }

    @Data
    public static class BillOverviewDto {
        private BigDecimal totalIncome;
        private BigDecimal totalExpenditure;
        private Map<String, BigDecimal> categoryExpenditure;
        private Integer billCount;
    }
}
