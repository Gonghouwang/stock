package com.weekie.service.impl;


import com.weekie.constant.MessageConstant;
import com.weekie.context.BaseContext;
import com.weekie.dto.TradeDTO;
import com.weekie.dto.UserDTO;
import com.weekie.entity.TradeHistroy;
import com.weekie.entity.TradeInfo;
import com.weekie.entity.User;
import com.weekie.exception.*;
import com.weekie.mapper.StockMapper;
import com.weekie.mapper.UserMapper;
import com.weekie.service.UserService;
import com.weekie.vo.BalanceVO;
import com.weekie.vo.TradeHistoryVO;
import com.weekie.vo.UserTradeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StockMapper stockMapper;


    public User login(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        User user=userMapper.getByUsername(username);

        if (user == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        if (!password.equals(user.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //3、返回实体对象
        return user;
    }


    @Override
    public void register(UserDTO userDTO) {
        try {
            userMapper.insert(userDTO);
        }
        catch (Exception ex){
            throw new AccountAlreadyExistException(MessageConstant.ALREADY_EXISTS);
        }

    }

    @Override
    public UserTradeVO getInfo() {
        Long id= BaseContext.getCurrentId();
        UserTradeVO userTradeVO=new UserTradeVO();
        User user=userMapper.getById(id);
        userTradeVO.setUsername(user.getUsername());
        userTradeVO.setBalance(user.getBalance());
        List<TradeInfo>tradeInfos=userMapper.getTradeInfo(id);
        for(TradeInfo info:tradeInfos){
        String code= stockMapper.findCodeById(info.getStockId());
        Double price=stockMapper.findStockSummaryByCode(code).getPrice();
        info.setPrice(price);
        info.setValue(info.getQuantity()*price);
        info.setProfit(info.getValue()+ info.getEarn()-info.getCost());
        }
        userTradeVO.setTradeInfo(tradeInfos);
        return userTradeVO;
    }

    @Override
    public UserTradeVO getInfoBySId(Long stockId) {
        Long id= BaseContext.getCurrentId();
        UserTradeVO userTradeVO=new UserTradeVO();
        User user=userMapper.getById(id);
        userTradeVO.setUsername(user.getUsername());
        userTradeVO.setBalance(user.getBalance());
        List<TradeInfo>tradeInfos=new ArrayList<>();
        TradeInfo info=userMapper.getTradeInfoBySId(id,stockId);
        if(info==null) return userTradeVO;
        String code= stockMapper.findCodeById(info.getStockId());
        Double price=stockMapper.findStockSummaryByCode(code).getPrice();
        info.setPrice(price);
        info.setValue(info.getQuantity()*price);
        info.setProfit(info.getValue()+ info.getEarn()-info.getCost());
        tradeInfos.add(info);
        userTradeVO.setTradeInfo(tradeInfos);
        return userTradeVO;
    }

    @Override
    public void buyStocks(TradeDTO tradeDTO) {
        Long id= BaseContext.getCurrentId();
        User user=userMapper.getById(id);
        Double balance=user.getBalance();
        String code= stockMapper.findCodeById(tradeDTO.getStockId());
        if(code==null) throw new NoSuchStockException("No Such Stock!");
        Double price=stockMapper.findStockSummaryByCode(code).getPrice();
        Double pay=tradeDTO.getQuantity()* price;
        if(balance.compareTo(pay)<0) throw new BalanceNotEnoughException("Balance Not Enough!");
        user.setBalance(balance-pay);
        userMapper.buyStock(id,tradeDTO.getStockId(),tradeDTO.getQuantity(),pay);
        userMapper.loggingTrade(new TradeHistroy(id,tradeDTO.getStockId(),0, tradeDTO.getQuantity(), price, LocalDateTime.now().toString()));
    }

    @Override
    public void sellStocks(TradeDTO tradeDTO) {
        Long id= BaseContext.getCurrentId();
        User user=userMapper.getById(id);
        Double balance=user.getBalance();
        String code= stockMapper.findCodeById(tradeDTO.getStockId());
        if(code==null) throw new NoSuchStockException("No Such Stock!");
        Double price=stockMapper.findStockSummaryByCode(code).getPrice();
        Integer quantity=userMapper.getTradeInfoBySId(id,tradeDTO.getStockId()).getQuantity();
        if(quantity==null||quantity.compareTo(tradeDTO.getQuantity())<0) throw new QuantityNotEnoughException("You Don't Hava Enough stocks to sell!");
        Double earn=tradeDTO.getQuantity()* price;
        user.setBalance(balance+earn);
        userMapper.sellStock(id,tradeDTO.getStockId(), tradeDTO.getQuantity(),earn);
        userMapper.loggingTrade(new TradeHistroy(id,tradeDTO.getStockId(),1, tradeDTO.getQuantity(), price, LocalDateTime.now().toString()));

    }

    @Override
    public TradeHistoryVO getTradeHistory() {
        TradeHistoryVO tradeHistoryVO=new TradeHistoryVO();
        Long usrId=BaseContext.getCurrentId();
        tradeHistoryVO.setHistory(userMapper.getTradeHistory(usrId));
        return tradeHistoryVO;
    }

    @Override
    public TradeHistoryVO getTradeHistoryBySId(Long stockId) {
        TradeHistoryVO tradeHistoryVO=new TradeHistoryVO();
        Long usrId=BaseContext.getCurrentId();
        tradeHistoryVO.setHistory(userMapper.getTradeHistoryBySId(usrId,stockId));
        return tradeHistoryVO;
    }

    @Override
    public BalanceVO getBalance() {
        Long id=BaseContext.getCurrentId();
        BalanceVO balanceVO=userMapper.getBalance(id);
        return balanceVO;
    }


}
