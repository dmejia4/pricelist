package com.vass.pricelist.controller;

import com.vass.pricelist.entity.Price;
import com.vass.pricelist.service.PriceService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log
@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {

    @Autowired
    PriceService priceService;

    @GetMapping({""})
    public ResponseEntity<Price> retrievePrices(@RequestParam String applicationDate, @RequestParam String productId,
                                                       @RequestParam int brandId) {
        Price price = priceService.retrievePrice(applicationDate, productId, brandId);
        if (price == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(price);
        }
    }
}
