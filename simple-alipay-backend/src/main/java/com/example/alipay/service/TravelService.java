package com.example.alipay.service;

import com.example.alipay.model.TravelPass;
import com.example.alipay.model.TravelRecord;
import com.example.alipay.model.UserAccount;
import com.example.alipay.repository.TravelPassRepository;
import com.example.alipay.repository.TravelRecordRepository;
import com.example.alipay.repository.UserAccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TravelService {
    private final TravelPassRepository passRepo;
    private final TravelRecordRepository recordRepo;
    private final UserAccountRepository userRepo;

    public TravelService(TravelPassRepository passRepo, TravelRecordRepository recordRepo, UserAccountRepository userRepo){
        this.passRepo = passRepo;
        this.recordRepo = recordRepo;
        this.userRepo = userRepo;
    }

    public TravelPass openTravelPass(String username, String city, String line, String defaultPayment){
        TravelPass p = new TravelPass();
        p.setUsername(username);
        p.setCity(city);
        p.setLine(line);
        p.setBoundPaymentMethod(defaultPayment);
        p.setEnabled(true);
        p.setUpdatedAt(LocalDateTime.now());
        passRepo.save(p);
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
        return r;
    }

    public TravelRecord exit(Long recordId){
        TravelRecord r = recordRepo.findById(recordId).orElseThrow();
        r.setExitTime(LocalDateTime.now());
        // simple fare calc: 2.5 fixed
        r.setFare(new BigDecimal("2.50"));
        r.setStatus("COMPLETED");
        // deduct from user's balance if exists
        UserAccount u = userRepo.findByUsername(r.getUsername()).orElse(null);
        if (u!=null){
            if (u.getBalance().compareTo(r.getFare())>=0){
                u.setBalance(u.getBalance().subtract(r.getFare()));
                userRepo.save(u);
            } else {
                r.setStatus("EXCEPTION");
            }
        }
        recordRepo.save(r);
        return r;
    }

    public List<TravelRecord> listRecords(String username){
        return recordRepo.findByUsernameOrderByEntryTimeDesc(username);
    }
}
