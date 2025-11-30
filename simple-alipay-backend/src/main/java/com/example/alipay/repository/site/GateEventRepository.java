package com.example.alipay.repository.site;

import com.example.alipay.model.site.GateEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GateEventRepository extends JpaRepository<GateEvent, Long> {
    List<GateEvent> findByGateIdOrderByEventTimeDesc(Long gateId);
    List<GateEvent> findByUserIdOrderByEventTimeDesc(Long userId);
    List<GateEvent> findByEventTypeOrderByEventTimeDesc(String eventType);
    List<GateEvent> findByStatusOrderByEventTimeDesc(String status);
    Optional<GateEvent> findByTransactionId(String transactionId);
}
