package com.vass.pricelist.service;

import com.vass.pricelist.entity.Price;
import org.springframework.transaction.annotation.Transactional;

public interface PriceService {
    @Transactional(readOnly = true)
    Price retrievePrice(String applicationDate, String productId, int brandId);
}
