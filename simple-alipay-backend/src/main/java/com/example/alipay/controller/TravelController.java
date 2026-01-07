package com.example.alipay.controller;

import com.example.alipay.model.TravelPass;
import com.example.alipay.model.TravelRecord;
import com.example.alipay.service.TravelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/travel")
public class TravelController {
    private final TravelService travelService;
    public TravelController(TravelService travelService){this.travelService = travelService;}

    @PostMapping("/open")
    public ResponseEntity<?> open(@RequestBody Map<String,String> body){
        String username = body.getOrDefault("username","alice");
        String city = body.getOrDefault("city","Beijing");
        String line = body.getOrDefault("line","Line1");
        String payment = body.getOrDefault("payment","balance");
        TravelPass p = travelService.openTravelPass(username,city,line,payment);
        return ResponseEntity.ok(p);
    }

    @PostMapping("/entry")
    public ResponseEntity<?> entry(@RequestBody Map<String,String> body){
        String username = body.getOrDefault("username","alice");
        String city = body.getOrDefault("city","Beijing");
        String line = body.getOrDefault("line","Line1");
        TravelRecord r = travelService.entry(username,city,line);
        return ResponseEntity.ok(r);
    }

    @PostMapping("/exit/{recordId}")
    public ResponseEntity<?> exit(@PathVariable Long recordId){
        TravelRecord r = travelService.exit(recordId);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/records/{username}")
    public ResponseEntity<?> records(@PathVariable String username){
        List<TravelRecord> list = travelService.listRecords(username);
        return ResponseEntity.ok(list);
    }
}
