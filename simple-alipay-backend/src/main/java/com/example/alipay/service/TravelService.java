package com.example.alipay.service;

import com.example.alipay.model.TravelPass;
import com.example.alipay.model.TravelRecord;
import com.example.alipay.repository.TravelPassRepository;
import com.example.alipay.repository.TravelRecordRepository;
import com.example.alipay.model.fintech.User;
import com.example.alipay.repository.fintech.UserRepository;
import com.example.alipay.model.fintech.BankCard;
import com.example.alipay.model.fintech.Bill;
import com.example.alipay.repository.fintech.BillRepository;
import com.example.alipay.repository.fintech.BankCardRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TravelService {
    private final TravelPassRepository passRepo;
    private final TravelRecordRepository recordRepo;
    private final UserRepository fintechUserRepo;
    private final BankCardRepository bankCardRepo;
    private final BillRepository billRepo;

    public TravelService(TravelPassRepository passRepo, TravelRecordRepository recordRepo, UserRepository fintechUserRepo, BankCardRepository bankCardRepo, BillRepository billRepo){
        this.passRepo = passRepo;
        this.recordRepo = recordRepo;
        this.fintechUserRepo = fintechUserRepo;
        this.bankCardRepo = bankCardRepo;
        this.billRepo = billRepo;
    }

    public TravelPass openTravelPass(String username, String city, String line, String defaultPayment){
        List<TravelPass> existing = passRepo.findByUsername(username);
        TravelPass p = null;
        if (existing != null && !existing.isEmpty()) {
            Optional<TravelPass> match = existing.stream().filter(tp -> city.equals(tp.getCity()) && line.equals(tp.getLine())).findFirst();
            p = match.orElse(existing.get(0));
            p.setCity(city);
            p.setLine(line);
            p.setBoundPaymentMethod(defaultPayment);
            p.setEnabled(true);
            p.setUpdatedAt(LocalDateTime.now());
            passRepo.save(p);
        } else {
            p = new TravelPass();
            p.setUsername(username);
            p.setCity(city);
            p.setLine(line);
            p.setBoundPaymentMethod(defaultPayment);
            p.setEnabled(true);
            p.setUpdatedAt(LocalDateTime.now());
            passRepo.save(p);
        }
        return p;
    }

    public TravelRecord entry(String username, String city, String line){
        TravelRecord r = new TravelRecord();
        r.setUsername(username);
        r.setCity(city);
        r.setLine(line);
        r.setEntryTime(LocalDateTime.now());
        r.setStatus("IN_PROGRESS");
        recordRepo.save(r);
        if ("COMPLETED".equals(r.getStatus())) {
            Optional<User> uOpt = fintechUserRepo.findByUsername(r.getUsername());
            if (uOpt.isPresent()) {
                Long userId = uOpt.get().getId();
                String ref = "TRV:" + r.getId();
                if (!billRepo.existsByUserIdAndSourceTypeAndSourceRefId(userId, "TRAVEL", ref)) {
                    Bill b = new Bill();
                    b.setUserId(userId);
                    b.setAmount(r.getFare());
                    b.setType("EXPENDITURE");
                    b.setCategory("交通");
                    b.setTransactionTime(LocalDateTime.now());
                    b.setRemark("地铁出站扣费");
                    b.setSourceType("TRAVEL");
                    b.setSourceRefId(ref);
                    billRepo.save(b);
                }
            }
        }
        return r;
    }

    public TravelRecord exit(Long recordId){
        TravelRecord r = recordRepo.findById(recordId).orElseThrow();
        r.setExitTime(LocalDateTime.now());
        r.setFare(new BigDecimal("2.50"));
        r.setStatus("COMPLETED");

        List<TravelPass> passes = passRepo.findByUsername(r.getUsername());
        String payMethod = (passes != null && !passes.isEmpty()) ? passes.get(0).getBoundPaymentMethod() : "balance";

        Optional<User> userOpt = fintechUserRepo.findByUsername(r.getUsername());
        if (userOpt.isPresent()) {
            User u = userOpt.get();
            if ("balance".equalsIgnoreCase(payMethod)) {
                if (u.getBalance() != null && u.getBalance().compareTo(r.getFare()) >= 0) {
                    u.setBalance(u.getBalance().subtract(r.getFare()));
                    fintechUserRepo.save(u);
                } else {
                    r.setStatus("EXCEPTION");
                }
            } else if ("card".equalsIgnoreCase(payMethod)) {
                List<BankCard> defaults = bankCardRepo.findByUserIdAndIsDefaultTrue(u.getId());
                if (defaults != null && !defaults.isEmpty()) {
                    // 模拟卡扣款成功（外部通道），不改动余额
                } else {
                    r.setStatus("EXCEPTION");
                }
            } else {
                r.setStatus("EXCEPTION");
            }
        } else {
            r.setStatus("EXCEPTION");
        }

        recordRepo.save(r);

        if ("COMPLETED".equals(r.getStatus())) {
            Optional<User> uOpt2 = fintechUserRepo.findByUsername(r.getUsername());
            if (uOpt2.isPresent()) {
                Long userId = uOpt2.get().getId();
                String ref = "TRV:" + r.getId();
                if (!billRepo.existsByUserIdAndSourceTypeAndSourceRefId(userId, "TRAVEL", ref)) {
                    Bill b = new Bill();
                    b.setUserId(userId);
                    b.setAmount(r.getFare());
                    b.setType("EXPENDITURE");
                    b.setCategory("交通");
                    b.setTransactionTime(LocalDateTime.now());
                    b.setRemark("地铁出站扣费");
                    b.setSourceType("TRAVEL");
                    b.setSourceRefId(ref);
                    billRepo.save(b);
                }
            }
        }
        return r;
    }

    public List<TravelRecord> listRecords(String username){
        return recordRepo.findByUsernameOrderByEntryTimeDesc(username);
    }
}
