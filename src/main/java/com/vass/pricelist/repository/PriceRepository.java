package com.vass.pricelist.repository;

import com.vass.pricelist.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {

    @Query(value = "SELECT * FROM prices p WHERE :applicationDate BETWEEN p.start_date AND p.end_date AND " +
            "p.product_id = :productId AND p.brand_Id = :brandId ORDER BY priority DESC", nativeQuery = true)
    List<Price> findByBrandIdProductIdAndApplicationDate(@Param(value = "applicationDate") Timestamp applicationDate, @Param(value = "productId") String productId, @Param(value = "brandId") int brandId);
}
