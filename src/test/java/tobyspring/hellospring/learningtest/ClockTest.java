package tobyspring.hellospring.learningtest;

import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockTest {

    @Test
    void clock() throws Exception {
        Clock clock = Clock.systemDefaultZone();
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        assertThat(dt1).isNotEqualTo(dt2);
    }

    @Test
    void fixedClock() throws Exception {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        assertThat(dt1).isEqualTo(dt2);
    }
}
