package com.example.alipay.service;

import com.example.alipay.model.Payment;
import com.example.alipay.model.UserAccount;
import com.example.alipay.model.fintech.Bill;
import com.example.alipay.model.fintech.User;
import com.example.alipay.repository.PaymentRepository;
import com.example.alipay.repository.UserAccountRepository;
import com.example.alipay.repository.fintech.BankCardRepository;
import com.example.alipay.repository.fintech.BillRepository;
import com.example.alipay.repository.fintech.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Test
    void balancePaymentCreditsUserAccountReceiverWhenUsernameMatches() {
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        UserAccountRepository userAccountRepository = mock(UserAccountRepository.class);
        BankCardRepository bankCardRepository = mock(BankCardRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        BillRepository billRepository = mock(BillRepository.class);

        when(paymentRepository.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        when(userAccountRepository.save(any(UserAccount.class))).thenAnswer(inv -> inv.getArgument(0));
        when(billRepository.existsByUserIdAndSourceTypeAndSourceRefId(anyLong(), anyString(), anyString())).thenReturn(false);
        when(billRepository.save(any(Bill.class))).thenAnswer(inv -> inv.getArgument(0));

        User from = new User();
        from.setId(1L);
        from.setUsername("payer");
        from.setBalance(new BigDecimal("100.00"));

        UserAccount to = new UserAccount();
        to.setId(2L);
        to.setUsername("receiver");
        to.setBalance(new BigDecimal("5.00"));

        when(userRepository.findByUsernameForUpdate("payer")).thenReturn(Optional.of(from));
        when(userRepository.findByUsernameForUpdate("receiver")).thenReturn(Optional.empty());
        when(userAccountRepository.findByUsernameForUpdate("receiver")).thenReturn(Optional.of(to));

        PaymentService service = new PaymentService(
                paymentRepository,
                userRepository,
                userAccountRepository,
                bankCardRepository,
                passwordEncoder,
                billRepository
        );

        Payment p = service.createAndExecutePayment(
                "payer",
                "receiver/6eee69b2-f3a8-45d9-8a0b-ab635371e8e1",
                new BigDecimal("10.00"),
                "balance",
                "balance",
                null,
                null,
                null
        );

        assertThat(p.getStatus()).isEqualTo("SUCCESS");
        assertThat(from.getBalance()).isEqualByComparingTo(new BigDecimal("90.00"));
        assertThat(to.getBalance()).isEqualByComparingTo(new BigDecimal("15.00"));
        verify(userRepository, atLeastOnce()).save(from);
        verify(userAccountRepository, atLeastOnce()).save(to);
    }

    @Test
    void receiveOnlyCreditsUserAccountReceiver() {
        PaymentRepository paymentRepository = mock(PaymentRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        UserAccountRepository userAccountRepository = mock(UserAccountRepository.class);
        BankCardRepository bankCardRepository = mock(BankCardRepository.class);
        PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);
        BillRepository billRepository = mock(BillRepository.class);

        when(paymentRepository.save(any(Payment.class))).thenAnswer(inv -> inv.getArgument(0));
        when(userRepository.save(any(User.class))).thenAnswer(inv -> inv.getArgument(0));
        when(userAccountRepository.save(any(UserAccount.class))).thenAnswer(inv -> inv.getArgument(0));

        UserAccount to = new UserAccount();
        to.setId(2L);
        to.setUsername("receiver");
        to.setBalance(new BigDecimal("5.00"));

        when(userRepository.findByUsernameForUpdate("receiver")).thenReturn(Optional.empty());
        when(userAccountRepository.findByUsernameForUpdate("receiver")).thenReturn(Optional.of(to));

        PaymentService service = new PaymentService(
                paymentRepository,
                userRepository,
                userAccountRepository,
                bankCardRepository,
                passwordEncoder,
                billRepository
        );

        Payment p = service.createAndExecutePayment(
                "any",
                "collect://receiver/6eee69b2-f3a8-45d9-8a0b-ab635371e8e1",
                new BigDecimal("10.00"),
                "balance",
                null,
                null,
                null,
                null
        );

        assertThat(p.getStatus()).isEqualTo("SUCCESS");
        assertThat(to.getBalance()).isEqualByComparingTo(new BigDecimal("15.00"));
        verify(userAccountRepository, atLeastOnce()).save(to);
    }
}
