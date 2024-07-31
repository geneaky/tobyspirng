package tobyspring.hellospring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.payment.Payment;
import tobyspring.hellospring.payment.PaymentService;

import java.util.concurrent.TimeUnit;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);
        Payment payment = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
        System.out.println(payment);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("====================================");
        Payment payment1 = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
        System.out.println(payment1);
        TimeUnit.SECONDS.sleep(3);
        System.out.println("====================================");
        Payment payment2 = paymentService.prepare(100L, "USD", java.math.BigDecimal.valueOf(50.7));
        System.out.println(payment2);
    }
}