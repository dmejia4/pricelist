package com.vass.pricelist.service;

import com.vass.pricelist.entity.Price;
import com.vass.pricelist.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(SpringExtension.class)
class PriceServiceImplTest {

    @InjectMocks
    PriceServiceImpl priceServiceImpl;
    @Mock
    PriceRepository priceRepository;

    @Test()
    void retrievePriceTest1() {
        Mockito.when(priceRepository.findByBrandIdProductIdAndApplicationDate(any(), anyString(), anyInt())).thenReturn(createPrice(1));

        Price price = priceServiceImpl.retrievePrice("2020-06-14 10:00:00", "35455", 1);

        assertEquals(35.50, price.getProductPrice());
        assertEquals(1, price.getPriceList());
        assertEquals(0, price.getPriority());

        System.out.println("\nTest 1: request at 10:00 on the 14th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }

    @Test()
    void retrievePriceTest2() {
        Mockito.when(priceRepository.findByBrandIdProductIdAndApplicationDate(any(), anyString(), anyInt())).thenReturn(createPrice(2));

        Price price = priceServiceImpl.retrievePrice("2020-06-14 16:00:00", "35455", 1);

        assertEquals(25.45, price.getProductPrice());
        assertEquals(2, price.getPriceList());
        assertEquals(1, price.getPriority());

        System.out.println("\nTest 2: request at 16:00 on the 14th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }

    @Test()
    void retrievePriceTest3() {
        Mockito.when(priceRepository.findByBrandIdProductIdAndApplicationDate(any(), anyString(), anyInt())).thenReturn(createPrice(3));

        Price price = priceServiceImpl.retrievePrice("2020-06-14 21:00:00", "35455", 1);

        assertEquals(35.50, price.getProductPrice());
        assertEquals(1, price.getPriceList());
        assertEquals(0, price.getPriority());

        System.out.println("\nTest 3: request at 21:00 on the 14th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }

    @Test()
    void retrievePriceTest4() {
        Mockito.when(priceRepository.findByBrandIdProductIdAndApplicationDate(any(), anyString(), anyInt())).thenReturn(createPrice(4));

        Price price = priceServiceImpl.retrievePrice("2020-06-15 10:00:00", "35455", 1);

        assertEquals(30.50, price.getProductPrice());
        assertEquals(3, price.getPriceList());
        assertEquals(1, price.getPriority());

        System.out.println("\nTest 4: request at 10:00 on the 15th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }

    @Test()
    void retrievePriceTest5() {
        Mockito.when(priceRepository.findByBrandIdProductIdAndApplicationDate(any(), anyString(), anyInt())).thenReturn(createPrice(5));

        Price price = priceServiceImpl.retrievePrice("2020-06-16 21:00:00", "35455", 1);

        assertEquals(38.95, price.getProductPrice());
        assertEquals(4, price.getPriceList());
        assertEquals(1, price.getPriority());

        System.out.println("\nTest 5: request at 16:00 on the 21th of product 35455 for brand 1 (ZARA). The resulting price is "
                + price.getProductPrice() + " and the start and end date of application is between the dates " + price.getStartDate()
                + " y " + price.getEndDate() + "\n");
    }

    private List<Price> createPrice(int testNumber) {
        List<Price> priceList = new ArrayList<>();
        Price price = new Price();

        if(testNumber == 1 || testNumber == 3){
            price.setId(1);
            price.setBrandId(1);
            price.setStartDate(Timestamp.valueOf("2020-06-14 00:00:00"));
            price.setEndDate(Timestamp.valueOf("2020-12-31 23:59:59"));
            price.setPriceList(1);
            price.setProductId("35455");
            price.setPriority(0);
            price.setProductPrice(35.50);
            price.setCurrency("EUR");

            priceList.add(price);
        }else if(testNumber == 2){
            price.setId(2);
            price.setBrandId(1);
            price.setStartDate(Timestamp.valueOf("2020-06-14 15:00:00"));
            price.setEndDate(Timestamp.valueOf("2020-06-14 18:30:00"));
            price.setPriceList(2);
            price.setProductId("35455");
            price.setPriority(1);
            price.setProductPrice(25.45);
            price.setCurrency("EUR");

            priceList.add(price);
        }else if(testNumber == 4){
            price.setId(3);
            price.setBrandId(1);
            price.setStartDate(Timestamp.valueOf("2020-06-15 00:00:00"));
            price.setEndDate(Timestamp.valueOf("2020-06-15 11:00:00"));
            price.setPriceList(3);
            price.setProductId("35455");
            price.setPriority(1);
            price.setProductPrice(30.50);
            price.setCurrency("EUR");

            priceList.add(price);
        }else if(testNumber == 5){
            price.setId(4);
            price.setBrandId(1);
            price.setStartDate(Timestamp.valueOf("2020-06-15 16:00:00"));
            price.setEndDate(Timestamp.valueOf("2020-12-31 23:59:59"));
            price.setPriceList(4);
            price.setProductId("35455");
            price.setPriority(1);
            price.setProductPrice(38.95);
            price.setCurrency("EUR");

            priceList.add(price);
        }

        return priceList;
    }
}