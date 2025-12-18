package com.example.alipay.repository;

import com.example.alipay.model.TravelPass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelPassRepository extends JpaRepository<TravelPass, Long> {
    List<TravelPass> findByUsername(String username);
}
