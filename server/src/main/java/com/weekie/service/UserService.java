package com.weekie.service;


import com.weekie.dto.TradeDTO;
import com.weekie.dto.UserDTO;
import com.weekie.entity.User;
import com.weekie.vo.BalanceVO;
import com.weekie.vo.TradeHistoryVO;
import com.weekie.vo.UserTradeVO;

public interface UserService {


    User login(UserDTO user);



    void register(UserDTO user);


    UserTradeVO getInfo();

    UserTradeVO getInfoBySId(Long stockId);

    void buyStocks(TradeDTO tradeDTO);

    void sellStocks(TradeDTO tradeDTO);

    TradeHistoryVO getTradeHistory();

    TradeHistoryVO getTradeHistoryBySId(Long stockId);

    BalanceVO getBalance();
}
