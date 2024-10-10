package com.weekie.mapper;

import com.weekie.entity.RecentPrice;
import com.weekie.entity.StockPredict;
import com.weekie.entity.StockSummary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StockMapper {

    // 获取所有不同的股票代码
    @Select("SELECT DISTINCT stock_code FROM stock.stock")
    List<String> findAllStockCodes();

    // 根据股票代码获取该股票的简要信息
    @Select("SELECT (SELECT stock_id from stockinfo where stockinfo.stock_code=#{stockCode}) AS stockId, stock_name,stock_code , " +
            "(SELECT price FROM stock WHERE stock_code = #{stockCode} ORDER BY time DESC LIMIT 1) AS price, " +
            "MAX(price) AS maxPrice, MIN(price) AS minPrice,"+"SUM(CASE WHEN DATE(time) = CURDATE() THEN volume ELSE 0 END) AS volume, "+
            "(SELECT price FROM stock WHERE stock_code = #{stockCode} AND DATE(time) = CURDATE() - INTERVAL 1 DAY " +
            "ORDER BY time DESC LIMIT 1) AS closingPrice, " +
            "(((SELECT price FROM stock WHERE stock_code = #{stockCode} ORDER BY time DESC LIMIT 1) / " +
            "(SELECT price FROM stock WHERE stock_code = #{stockCode} AND DATE(time) = CURDATE() - INTERVAL 1 DAY " +
            "ORDER BY time DESC LIMIT 1)) - 1) * 100 AS changePercent " +
            "FROM stock WHERE stock_code = #{stockCode} " +
            "GROUP BY  stock_name ,stock_code " )
    StockSummary findStockSummaryByCode( String stockCode);

    @Select("select stock_code from stockinfo where  stock_id=#{stockId}")
    String findCodeById(Long stockId);

    @Select("select stock_id from stockinfo where  stock_code=#{stockCode}")
    Long findIdByCode(String stockCode);

    @Insert("insert into stockinfo (stock_code) values (#{stockCode})")
    void insertStockId(String stockCode);

    @Select("SELECT time, price, volume FROM stock.stock WHERE stock_code = #{stockCode} ORDER BY time DESC LIMIT 10")
    List<RecentPrice> findRecentPricesByCode( String stockCode);

}
