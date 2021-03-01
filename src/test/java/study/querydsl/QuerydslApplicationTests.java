package study.querydsl;

import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@SpringBootTest
class QuerydslApplicationTests {
    @PersistenceContext
    EntityManager em;

    @Test
    void contextLoads() {
        Hello hello = new Hello();
        em.persist(hello);
        JPQLQueryFactory query = new JPAQueryFactory(em);
        Hello result = query
                .selectFrom(QHello.hello)
                .fetchOne();

        Assertions.assertThat(result).isEqualTo(hello);
        Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
    }

}
