package tobyspring.hellospring.exrate;

import tobyspring.hellospring.payment.ExRateProvider;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class CachedExRateProvider implements ExRateProvider {

    private final ExRateProvider target;
    private final Map<String, BigDecimal> cache = new HashMap<>();
    private LocalDateTime cacheExpiryTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExRate(String currency) {
        if (!cache.containsKey(currency) || cacheExpiryTime.isBefore(LocalDateTime.now())) {
            BigDecimal exRate = target.getExRate(currency);
            cache.put(currency, exRate);
            cacheExpiryTime = LocalDateTime.now().plusSeconds(3);
            return exRate;
        } else {
            return cache.get(currency);
        }
    }
}
