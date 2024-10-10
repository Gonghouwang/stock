package com.weekie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradeInfo {
        private Long stockId;
        private String stockName;
        private Double cost;
        private Integer quantity;
        private Double price;
        private Double value;
        private Double earn;
        private Double profit;
}
