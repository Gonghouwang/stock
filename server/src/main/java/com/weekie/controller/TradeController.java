package com.weekie.controller;

import com.weekie.dto.TradeDTO;
import com.weekie.entity.StockDetail;
import com.weekie.entity.StockPredict;
import com.weekie.entity.StockSummary;
import com.weekie.result.Result;
import com.weekie.service.StockService;
import com.weekie.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private UserService userService;

    @PostMapping("/buy")
    public Result<String> buyStocks(@RequestBody TradeDTO tradeDTO) {
        userService.buyStocks(tradeDTO);
        return Result.success();
    }
    @PostMapping("/sell")
    public Result<String> sellStocks(@RequestBody TradeDTO tradeDTO) {
        userService.sellStocks(tradeDTO);
        return Result.success();
    }

}