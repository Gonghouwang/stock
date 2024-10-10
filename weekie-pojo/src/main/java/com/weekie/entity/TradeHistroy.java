package com.weekie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeHistroy {
    private Long userId;
    private Long stockId;
    private Integer tradeType;
    private Integer tradeQuantity;
    private Double tradePrice;
    private String tradeDate;
}
