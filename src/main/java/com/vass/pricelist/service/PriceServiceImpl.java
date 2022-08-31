package com.vass.pricelist.service;

import com.vass.pricelist.entity.Price;
import com.vass.pricelist.repository.PriceRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;

@Log
@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    PriceRepository priceRepository;

    @Override
    @Transactional(readOnly = true)
    public Price retrievePrice(String applicationDate, String productId, int brandId) {
        return priceRepository.findByBrandIdProductIdAndApplicationDate(Timestamp.valueOf(applicationDate), productId, brandId)
                .get(0);
    }
}
