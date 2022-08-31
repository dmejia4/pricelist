package com.vass.pricelist.repository;

import com.vass.pricelist.entity.Price;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.persistence.EntityManager;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PriceRepositoryTest {

    @Autowired
    PriceRepository priceRepository;
    @Autowired
    EntityManager entityManager;

    @BeforeEach
    void setUp() {
        entityManager.createNativeQuery("CREATE TABLE IF NOT EXISTS prices (id integer NOT NULL, brand_id integer, start_date timestamp without time zone NOT NULL, end_date timestamp without time zone NOT NULL, price_list integer NOT NULL, product_id character NOT NULL, priority integer, price double precision NOT NULL, curr character NOT NULL, CONSTRAINT prices_pkey PRIMARY KEY (id))").executeUpdate();
        entityManager.createNativeQuery("INSERT INTO prices(id, brand_id, start_date, end_date, price_list, product_id, priority, price, curr) " +
                                           "VALUES (1, 1, '2020-06-14 00:00:00', '2020-12-31 23:59:59', 1, '35455', 0, 35.50, 'EUR')," +
                                                  "(2, 1, '2020-06-14 15:00:00', '2020-06-14 18:30:00', 2, '35455', 1, 25.45, 'EUR')," +
                                                  "(3, 1, '2020-06-15 00:00:00', '2020-06-15 11:00:00', 3, '35455', 1, 30.50, 'EUR')," +
                                                  "(4, 1, '2020-06-15 16:00:00', '2020-12-31 23:59:59', 4, '35455', 1, 38.95, 'EUR')").executeUpdate();
    }



    @Test
    void findByBrandIdProductIdAndApplicationDateTest1() {
        Price price = priceRepository.findByBrandIdProductIdAndApplicationDate(Timestamp.valueOf("2020-06-14 10:00:00"), "35455", 1).get(0);
        assertEquals(35.50, price.getProductPrice());
        assertEquals(1, price.getPriceList());
        assertEquals(0, price.getPriority());

        System.out.println("\nTest 1: request at 10:00 on the 14th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }

    @Test
    void findByBrandIdProductIdAndApplicationDateTest2() {
        Price price = priceRepository.findByBrandIdProductIdAndApplicationDate(Timestamp.valueOf("2020-06-14 16:00:00"), "35455", 1).get(0);
        assertEquals(25.45, price.getProductPrice());
        assertEquals(2, price.getPriceList());
        assertEquals(1, price.getPriority());

        System.out.println("\nTest 2: request at 16:00 on the 14th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }

    @Test
    void findByBrandIdProductIdAndApplicationDateTest3() {
        Price price = priceRepository.findByBrandIdProductIdAndApplicationDate(Timestamp.valueOf("2020-06-14 21:00:00"), "35455", 1).get(0);
        assertEquals(35.50, price.getProductPrice());
        assertEquals(1, price.getPriceList());
        assertEquals(0, price.getPriority());

        System.out.println("\nTest 3: request at 21:00 on the 14th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }

    @Test
    void findByBrandIdProductIdAndApplicationDateTest4() {
        Price price = priceRepository.findByBrandIdProductIdAndApplicationDate(Timestamp.valueOf("2020-06-15 10:00:00"), "35455", 1).get(0);
        assertEquals(30.50, price.getProductPrice());
        assertEquals(3, price.getPriceList());
        assertEquals(1, price.getPriority());

        System.out.println("\nTest 4: request at 10:00 on the 15th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }

    @Test
    void findByBrandIdProductIdAndApplicationDateTest5() {
        Price price = priceRepository.findByBrandIdProductIdAndApplicationDate(Timestamp.valueOf("2020-06-16 21:00:00"), "35455", 1).get(0);
        assertEquals(38.95, price.getProductPrice());
        assertEquals(4, price.getPriceList());
        assertEquals(1, price.getPriority());

        System.out.println("\nTest 5: request at 16:00 on the 21th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }
}