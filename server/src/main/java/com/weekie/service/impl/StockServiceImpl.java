package com.weekie.service.impl;

import com.weekie.entity.StockDetail;
import com.weekie.entity.StockPredict;
import com.weekie.entity.StockSummary;
import com.weekie.mapper.StockMapper;
import com.weekie.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockMapper stockMapper;

    public List<StockSummary> getAllStockSummaries() {
        // Step 1: 获取所有不同的股票代码
        List<String> stockCodes = stockMapper.findAllStockCodes();
        // Step 2: 根据每个股票代码获取简要信息
        List<StockSummary> stockSummaries = new ArrayList<>();
        for (String stockCode : stockCodes) {
            if(stockMapper.findIdByCode(stockCode)==null) stockMapper.insertStockId(stockCode);
            StockSummary summary = stockMapper.findStockSummaryByCode(stockCode);
            if (summary != null) {
                stockSummaries.add(summary);
            }
        }

        return stockSummaries;
    }

    @Override
    public StockDetail getStockDetail(Long stockId) {
        String code=stockMapper.findCodeById(stockId);
        StockDetail stockDetail=new StockDetail();
        stockDetail.setStockSummary(stockMapper.findStockSummaryByCode(code));
        stockDetail.setRecentPrices(stockMapper.findRecentPricesByCode(code));
        return stockDetail;
    }

    @Override
    public StockPredict getStockPredict(Long stockId) {
        return null;
    }
}
