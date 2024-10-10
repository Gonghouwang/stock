package com.weekie.mapper;

import com.weekie.dto.UserDTO;
import com.weekie.entity.TradeHistroy;
import com.weekie.entity.TradeInfo;
import com.weekie.entity.User;
import com.weekie.vo.BalanceVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from stock.user where username = #{username}")
    User getByUsername(String username);


    @Insert("insert into stock.user (username, password,balance) values "
            + "(#{username}, #{password},5000)")
    void insert(UserDTO userDTO);

    @Select("select * from stock.user where id = #{id}")
    User getById(Long id);
    @Select("select * from stock.trade where usr_id = #{id}")
    List<TradeInfo> getTradeInfo(Long id);
    @Select("select * from stock.trade where usr_id = #{id} and stock_id=#{stockId} LIMIT 1")
    TradeInfo getTradeInfoBySId(Long id, Long stockId);

    @Insert("INSERT INTO stock.trade (usr_id, stock_id, quantity, cost,earn) " +
            "VALUES (#{id}, #{stockId}, #{quantity}, #{pay},0) " +
            "ON DUPLICATE KEY UPDATE " +
            "quantity = quantity + #{quantity}, " +
            "cost = cost +#{pay} ")
    void buyStock(Long id, Long stockId, Integer quantity, Double pay);

   @Update("update trade set quantity=quantity-#{quantity}, earn=earn+#{earn} where usr_id=#{id} and stock_id=#{stockId}" )
    void sellStock(Long id, Long stockId, Integer quantity, Double earn);
    @Insert("insert into trade_history (user_id, stock_id, trade_type, trade_quantity, trade_price, trade_date) "+
    " VALUES (#{userId},#{stockId},#{tradeType},#{tradeQuantity},#{tradePrice},#{tradeDate})")
    void loggingTrade(TradeHistroy tradeHistroy);

    @Select("select * from trade_history where user_id=#{id}")
    List<TradeHistroy> getTradeHistory(Long id);
    @Select("select * from trade_history where user_id=#{id} and stock_id=#{stockId}")
    List<TradeHistroy> getTradeHistoryBySId(Long usrId, Long stockId);

    @Select("select username,balance from user where id=#{id}")
    BalanceVO getBalance(Long id);
}
