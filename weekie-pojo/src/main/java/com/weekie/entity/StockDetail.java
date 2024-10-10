package com.weekie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockDetail {
    private StockSummary stockSummary;
    private List<RecentPrice> recentPrices;
}
