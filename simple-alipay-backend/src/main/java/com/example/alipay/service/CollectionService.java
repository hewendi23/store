package com.example.alipay.service;

import com.example.alipay.model.CollectionQRCode;
import com.example.alipay.repository.CollectionQRCodeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class CollectionService {
    private final CollectionQRCodeRepository repo;
    private final QrService qrService;

    public CollectionService(CollectionQRCodeRepository repo, QrService qrService){
        this.repo = repo;
        this.qrService = qrService;
    }

    public CollectionQRCode generateInitialQRCode(String merchantId, int validSeconds){
        CollectionQRCode q = new CollectionQRCode();
        q.setMerchantId(merchantId);
        String content = "collect://" + merchantId + "/" + UUID.randomUUID();
        q.setContent(content);
        q.setExpireAt(LocalDateTime.now().plusSeconds(validSeconds));
        q.setActive(true);
        repo.save(q);
        return q;
    }

    public CollectionQRCode refreshQRCode(Long id, int validSeconds){
        CollectionQRCode q = repo.findById(id).orElseThrow();
        q.setContent("collect://" + q.getMerchantId() + "/" + UUID.randomUUID());
        q.setExpireAt(LocalDateTime.now().plusSeconds(validSeconds));
        q.setActive(true);
        repo.save(q);
        return q;
    }

    public boolean isValid(CollectionQRCode q){
        return q != null && q.isActive() && q.getExpireAt()!=null && q.getExpireAt().isAfter(LocalDateTime.now());
    }
}
