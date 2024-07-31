package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;

public class ApiTemplate {

    private final ApiExecutor apiExecutor;
    private final ExRateExtractor exRateExtractor;

    public ApiTemplate(ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        this.apiExecutor = apiExecutor;
        this.exRateExtractor = exRateExtractor;
    }

    public ApiTemplate() {
        this.apiExecutor = new HttpClientApiExecutor();
        this.exRateExtractor = new ErApiExRateExtractor();
    }

    public BigDecimal getExRate(String url) {
        return this.getExRate(url, this.apiExecutor, this.exRateExtractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor) {
        return this.getExRate(url, apiExecutor, this.exRateExtractor);
    }

    public BigDecimal getExRate(String url, ExRateExtractor exRateExtractor) {
        return this.getExRate(url, this.apiExecutor, exRateExtractor);
    }

    public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        URI uri = URI.create(url);
        String response;
        try {
            response = apiExecutor.execute(uri);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
