package jpabook.jpashop;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
        initService.dbInit2();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;

        public void dbInit1() {
            Member member = createMember("userA", "서울", "1", "1111");
            em.persist(member);

            Item book1 = createBook("JPA1 BOOK", 10000);
            em.persist(book1);

            Item book2 = createBook("JPA2 BOOK", 20000);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);


        }

        private Delivery createDelivery(Member member) {
            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());
            return delivery;
        }

        private Item createBook(String s, int i) {
            Book book1 = new Book();
            book1.setName(s);
            book1.setPrice(i);
            book1.setStockQuantity(100);
            return book1;
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

        public void dbInit2() {
            Member member = createMember("userB", "진주", "2", "2222");
            em.persist(member);

            Item book1 = createBook("SPRING1 BOOK", 10000);
            em.persist(book1);

            Item book2 = createBook("SPRING2 BOOK", 20000);
            em.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = createDelivery(member);
            Order order = Order.createOrder(member, delivery, orderItem1, orderItem2);
            em.persist(order);

        }

    }
}


