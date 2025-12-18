package com.example.alipay.controller;

import com.example.alipay.model.CollectionQRCode;
import com.example.alipay.service.CollectionService;
import com.example.alipay.service.QrService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/api/collect")
public class CollectionController {
    private final CollectionService collectionService;
    private final QrService qrService;
    public CollectionController(CollectionService collectionService, QrService qrService){
        this.collectionService = collectionService;
        this.qrService = qrService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Map<String,String> body) throws Exception {
        String merchantId = body.getOrDefault("merchantId","m1");
        int validSeconds = Integer.parseInt(body.getOrDefault("validSeconds","120"));
        CollectionQRCode q = collectionService.generateInitialQRCode(merchantId, validSeconds);
        String base64 = qrService.generateQrBase64(q.getContent(),300,300);
        return ResponseEntity.ok(Map.of("qrBase64", base64, "id", q.getId(), "expireAt", q.getExpireAt().toString()));
    }

    @PostMapping("/refresh/{id}")
    public ResponseEntity<?> refresh(@PathVariable Long id, @RequestBody Map<String,String> body) throws Exception {
        int validSeconds = Integer.parseInt(body.getOrDefault("validSeconds","120"));
        CollectionQRCode q = collectionService.refreshQRCode(id, validSeconds);
        String base64 = qrService.generateQrBase64(q.getContent(),300,300);
        return ResponseEntity.ok(Map.of("qrBase64", base64, "id", q.getId(), "expireAt", q.getExpireAt().toString()));
    }

    @PostMapping("/parseFromBase64")
    public ResponseEntity<?> parseFromBase64(@RequestBody Map<String,String> body) throws Exception {
        String b64 = body.get("imageBase64");
        byte[] bytes = Base64.getDecoder().decode(b64);
        String text = qrService.parseQrFromBytes(bytes);
        return ResponseEntity.ok(Map.of("text", text));
    }
}
