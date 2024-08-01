package tobyspring.hellospring.data;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import tobyspring.hellospring.order.Order;
import tobyspring.hellospring.order.OrderRepository;

import javax.sql.DataSource;

public class JdbcOrderRepository implements OrderRepository {

    private final JdbcClient jdbcClient;

    public JdbcOrderRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    @PostConstruct
    public void initDb() {
        jdbcClient.sql("""
                create table orders (
                    id bigint not null primary key,
                    no varchar(255),
                    total decimal(19, 2)
                );
                                
                create sequence orders_SEQ start with 1 increment by 50;
                """);
    }

    @Override
    public void save(Order order) {
        Long id = jdbcClient.sql("select next value for orders_SEQ").query(Long.class).single();
        System.out.println(id);
        order.setId(id);
        jdbcClient.sql("""
                        insert into orders (id, no, total) values (?, ?, ?)
                        """).params(order.getId(), order.getNo(), order.getTotal())
                .update();
    }
}
