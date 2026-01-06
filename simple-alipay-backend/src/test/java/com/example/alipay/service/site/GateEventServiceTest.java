package com.example.alipay.service.site;

import com.example.alipay.model.admin.DiscountPolicy;
import com.example.alipay.model.fintech.User;
import com.example.alipay.model.fintech.UserStatus;
import com.example.alipay.model.fintech.Bill;
import com.example.alipay.model.TravelRecord;
import com.example.alipay.model.site.Gate;
import com.example.alipay.model.site.GateEvent;
import com.example.alipay.model.site.Station;
import com.example.alipay.repository.admin.DiscountPolicyRepository;
import com.example.alipay.repository.TravelRecordRepository;
import com.example.alipay.repository.fintech.BillRepository;
import com.example.alipay.repository.fintech.UserRepository;
import com.example.alipay.repository.site.GateEventRepository;
import com.example.alipay.repository.site.GateRepository;
import com.example.alipay.repository.site.StationRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GateEventServiceTest {

    @Test
    void exitComputesStationCountFareAndAppliesBestDiscount() {
        GateRepository gateRepository = mock(GateRepository.class);
        GateEventRepository gateEventRepository = mock(GateEventRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        BillRepository billRepository = mock(BillRepository.class);
        StationRepository stationRepository = mock(StationRepository.class);
        DiscountPolicyRepository discountPolicyRepository = mock(DiscountPolicyRepository.class);
        TravelRecordRepository travelRecordRepository = mock(TravelRecordRepository.class);

        Gate exitGate = new Gate();
        exitGate.setId(10L);
        exitGate.setGateCode("G_EXIT");
        exitGate.setDirection("EXIT");
        exitGate.setEnabled(true);
        exitGate.setStatus("ONLINE");
        exitGate.setStationId(200L);

        Gate entryGate = new Gate();
        entryGate.setId(11L);
        entryGate.setGateCode("G_ENTRY");
        entryGate.setDirection("ENTRY");
        entryGate.setEnabled(true);
        entryGate.setStatus("ONLINE");
        entryGate.setStationId(100L);

        User user = new User();
        user.setId(1L);
        user.setUsername("u1");
        user.setStatus(UserStatus.ACTIVE);
        user.setBalance(new BigDecimal("100.00"));

        GateEvent latestEntry = new GateEvent();
        latestEntry.setGateId(entryGate.getId());
        latestEntry.setUserId(user.getId());
        latestEntry.setEventType("ENTRY");
        latestEntry.setStatus("SUCCESS");
        latestEntry.setEventTime(LocalDateTime.now().minusMinutes(5));

        Station entryStation = new Station();
        entryStation.setId(100L);
        entryStation.setLine("Line1");
        entryStation.setLocation("1");

        Station exitStation = new Station();
        exitStation.setId(200L);
        exitStation.setLine("Line1");
        exitStation.setLocation("4");

        DiscountPolicy p1 = new DiscountPolicy();
        p1.setEnabled(true);
        p1.setDiscountRate(new BigDecimal("0.90"));
        p1.setApplicableLines("Line1");

        DiscountPolicy p2 = new DiscountPolicy();
        p2.setEnabled(true);
        p2.setDiscountRate(new BigDecimal("0.80"));
        p2.setApplicableLines("Line1");

        when(gateRepository.findTopByGateCodeAndEnabledTrueOrderByUpdatedAtDesc("G_EXIT")).thenReturn(Optional.of(exitGate));
        when(gateRepository.findTopByGateCodeOrderByUpdatedAtDesc("G_EXIT")).thenReturn(Optional.empty());
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(gateEventRepository.findTopByUserIdAndEventTypeAndStatusOrderByEventTimeDesc(1L, "ENTRY", "SUCCESS"))
                .thenReturn(Optional.of(latestEntry));
        when(gateEventRepository.findTopByUserIdAndEventTypeAndStatusOrderByEventTimeDesc(1L, "EXIT", "SUCCESS"))
                .thenReturn(Optional.empty());
        when(gateRepository.findById(entryGate.getId())).thenReturn(Optional.of(entryGate));
        when(stationRepository.findById(100L)).thenReturn(Optional.of(entryStation));
        when(stationRepository.findById(200L)).thenReturn(Optional.of(exitStation));
        when(discountPolicyRepository.findByEnabledTrue()).thenReturn(List.of(p1, p2));

        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        when(gateEventRepository.save(any(GateEvent.class))).thenAnswer(inv -> inv.getArgument(0));
        when(billRepository.existsByUserIdAndSourceTypeAndSourceRefId(anyLong(), anyString(), anyString())).thenReturn(false);
        when(billRepository.save(any(Bill.class))).thenAnswer(inv -> inv.getArgument(0));
        when(travelRecordRepository.findTopByUsernameAndStatusOrderByEntryTimeDesc("u1", "IN_PROGRESS")).thenReturn(Optional.empty());
        when(travelRecordRepository.save(any(TravelRecord.class))).thenAnswer(inv -> inv.getArgument(0));

        GateEventService service = new GateEventService(
                gateRepository,
                gateEventRepository,
                userRepository,
                billRepository,
                stationRepository,
                discountPolicyRepository,
                travelRecordRepository
        );

        GateEvent result = service.processExit("G_EXIT", "1", null);

        assertThat(result.getStatus()).isEqualTo("SUCCESS");
        assertThat(result.getFare()).isEqualByComparingTo(new BigDecimal("7.20"));
        assertThat(user.getBalance()).isEqualByComparingTo(new BigDecimal("92.80"));
        verify(userRepository, atLeastOnce()).save(user);
        verify(gateEventRepository, atLeastOnce()).save(any(GateEvent.class));
    }

    @Test
    void exitFallsBackWhenNoEnabledGateWithSameCode() {
        GateRepository gateRepository = mock(GateRepository.class);
        GateEventRepository gateEventRepository = mock(GateEventRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        BillRepository billRepository = mock(BillRepository.class);
        StationRepository stationRepository = mock(StationRepository.class);
        DiscountPolicyRepository discountPolicyRepository = mock(DiscountPolicyRepository.class);
        TravelRecordRepository travelRecordRepository = mock(TravelRecordRepository.class);

        Gate exitGate = new Gate();
        exitGate.setId(10L);
        exitGate.setGateCode("G_EXIT");
        exitGate.setDirection("EXIT");
        exitGate.setEnabled(false);
        exitGate.setStatus("OFFLINE");

        when(gateRepository.findTopByGateCodeAndEnabledTrueOrderByUpdatedAtDesc("G_EXIT")).thenReturn(Optional.empty());
        when(gateRepository.findTopByGateCodeOrderByUpdatedAtDesc("G_EXIT")).thenReturn(Optional.of(exitGate));
        when(gateEventRepository.save(any(GateEvent.class))).thenAnswer(inv -> inv.getArgument(0));

        GateEventService service = new GateEventService(
                gateRepository,
                gateEventRepository,
                userRepository,
                billRepository,
                stationRepository,
                discountPolicyRepository,
                travelRecordRepository
        );

        GateEvent result = service.processExit("G_EXIT", "1", null);
        assertThat(result.getStatus()).isEqualTo("FAILED");
        assertThat(result.getErrorCode()).isEqualTo("GATE_OFFLINE");
    }

    @Test
    void exitUsesStationIdHintToResolveGate() {
        GateRepository gateRepository = mock(GateRepository.class);
        GateEventRepository gateEventRepository = mock(GateEventRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        BillRepository billRepository = mock(BillRepository.class);
        StationRepository stationRepository = mock(StationRepository.class);
        DiscountPolicyRepository discountPolicyRepository = mock(DiscountPolicyRepository.class);
        TravelRecordRepository travelRecordRepository = mock(TravelRecordRepository.class);

        Gate stationSpecific = new Gate();
        stationSpecific.setId(6L);
        stationSpecific.setStationId(3L);
        stationSpecific.setGateCode("2");
        stationSpecific.setDirection("EXIT");
        stationSpecific.setEnabled(false);
        stationSpecific.setStatus("OFFLINE");

        Gate globalEnabled = new Gate();
        globalEnabled.setId(4L);
        globalEnabled.setStationId(2L);
        globalEnabled.setGateCode("2");
        globalEnabled.setDirection("EXIT");
        globalEnabled.setEnabled(true);
        globalEnabled.setStatus("ONLINE");

        when(gateRepository.findTopByStationIdAndGateCodeAndEnabledTrueOrderByUpdatedAtDesc(3L, "2")).thenReturn(Optional.empty());
        when(gateRepository.findTopByStationIdAndGateCodeOrderByUpdatedAtDesc(3L, "2")).thenReturn(Optional.of(stationSpecific));
        when(gateRepository.findTopByGateCodeAndEnabledTrueOrderByUpdatedAtDesc("2")).thenReturn(Optional.of(globalEnabled));
        when(gateEventRepository.save(any(GateEvent.class))).thenAnswer(inv -> inv.getArgument(0));

        GateEventService service = new GateEventService(
                gateRepository,
                gateEventRepository,
                userRepository,
                billRepository,
                stationRepository,
                discountPolicyRepository,
                travelRecordRepository
        );

        GateEvent result = service.processExit("2", "1", 3L);
        assertThat(result.getStatus()).isEqualTo("FAILED");
        assertThat(result.getErrorCode()).isEqualTo("GATE_OFFLINE");
        verify(gateRepository).findTopByStationIdAndGateCodeOrderByUpdatedAtDesc(3L, "2");
    }
}
