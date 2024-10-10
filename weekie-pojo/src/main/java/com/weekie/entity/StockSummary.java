package com.weekie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockSummary implements Serializable {
    private Long stockId;
    private String stockName;
    private String stockCode;
    private Double Price;
    private Double maxPrice;
    private Double minPrice;
    private Long volume;
    private Long changePercent;
    private Double closingPrice;
}
