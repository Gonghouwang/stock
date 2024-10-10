package com.weekie.service;


import com.weekie.entity.StockDetail;
import com.weekie.entity.StockPredict;
import com.weekie.entity.StockSummary;

import java.util.List;

public interface StockService {


    public List<StockSummary> getAllStockSummaries();


    StockDetail getStockDetail(Long stockId);

    StockPredict getStockPredict(Long stockId);
}
