package tobyspring.hellospring.payment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentServiceTest {
    Clock clock;

    @BeforeEach
    void beforeEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }

    @Test
    void prepare() throws Exception {
        testAMount(BigDecimal.valueOf(500), BigDecimal.valueOf(5000), clock);
        testAMount(BigDecimal.valueOf(1000), BigDecimal.valueOf(10000), clock);
        testAMount(BigDecimal.valueOf(3000), BigDecimal.valueOf(30000), clock);
    }

    @Test
    void validUntil() throws Exception {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(BigDecimal.valueOf(1000)), clock);

        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);

        assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }

    private void testAMount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
        Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
    }
}