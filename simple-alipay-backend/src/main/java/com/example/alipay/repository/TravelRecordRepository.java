package com.example.alipay.repository;

import com.example.alipay.model.TravelRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TravelRecordRepository extends JpaRepository<TravelRecord, Long> {
    List<TravelRecord> findByUsernameOrderByEntryTimeDesc(String username);
    Optional<TravelRecord> findTopByUsernameAndStatusOrderByEntryTimeDesc(String username, String status);
}
