package com.vass.pricelist.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "prices")
public class Price {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "brand_id", length = 5, nullable = false)
    private int brandId;
    @Column(name = "start_date", length = 25, nullable = false)
    @JsonFormat(timezone = "Europe/Madrid", pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp startDate;
    @JsonFormat(timezone = "Europe/Madrid", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "end_date", length = 25, nullable = false)
    private Timestamp endDate;
    @Column(name = "price_list", length = 10, nullable = false)
    private int priceList;
    @Column(name = "product_id", length = 10, nullable = false)
    private String productId;
    @JsonIgnore
    @Column(name = "priority", length = 10)
    private int priority;
    @Column(name = "price", length = 30, nullable = false)
    private double productPrice;
    @JsonIgnore
    @Column(name = "curr", length = 5, nullable = false)
    private String currency;
}
