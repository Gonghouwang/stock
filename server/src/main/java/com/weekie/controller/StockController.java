package com.weekie.controller;

import com.weekie.entity.StockDetail;
import com.weekie.entity.StockPredict;
import com.weekie.entity.StockSummary;
import com.weekie.result.Result;
import com.weekie.service.StockService;
import com.weekie.vo.StockSummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public Result<StockSummaryVO> getAllStocks() {
        List<StockSummary> stockSummaries = stockService.getAllStockSummaries();
        StockSummaryVO stocks=new StockSummaryVO();
        stocks.setStocks(stockSummaries);
        return Result.success(stocks);
    }
    @GetMapping("/{stockId}")
    public Result<StockDetail> getStockDetail(@PathVariable Long stockId) {
        StockDetail stockDetail = stockService.getStockDetail(stockId);
        return Result.success(stockDetail);
    }

    @GetMapping("/predict/{stockId}")
    public Result<StockPredict> getStockPredict(@PathVariable Long stockId) {
        StockPredict stockPredict = stockService.getStockPredict(stockId);
        return Result.success(stockPredict);
    }
}
