package com.vass.pricelist.controller;

import com.vass.pricelist.entity.Price;
import com.vass.pricelist.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PriceController.class)
class PriceControllerTest {

    @MockBean
    PriceService priceService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void retrievePrices() {
        try {
            Price price = new Price();
            Mockito.when(priceService.retrievePrice(anyString(), anyString(), anyInt())).thenReturn(price);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prices")
                            .queryParam("applicationDate", "2020-06-14 10:00:00")
                            .queryParam("productId", "35455")
                            .queryParam("brandId", "1"))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void retrievePricesNotFound() {
        try {
            Mockito.when(priceService.retrievePrice(anyString(), anyString(), anyInt())).thenReturn(null);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/prices")
                            .queryParam("applicationDate", "2021-06-14 10:00:00")
                            .queryParam("productId", "35455")
                            .queryParam("brandId", "1"))
                    .andExpect(MockMvcResultMatchers.status().isNotFound());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}