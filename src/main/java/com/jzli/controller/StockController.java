package com.jzli.controller;

import com.jzli.bean.PageInfo;
import com.jzli.bean.RecommendStockInfo;
import com.jzli.bean.StockInfo;
import com.jzli.bean.StockRecord;
import com.jzli.service.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * =======================================================
 *
 * @Company 产品技术部
 * @Date ：2018/2/1
 * @Author ：李金钊
 * @Version ：0.0.1
 * @Description ：
 * ========================================================
 */
@RestController
@RequestMapping("/stock")
@Api(value = "/stock", description = "股票")
public class StockController {
    @Autowired
    private StockService stockService;

    @RequestMapping("/get")
    @ApiOperation(value = "获取个股信息", httpMethod = "GET", notes = "获取个股信息")
    public StockInfo get(@RequestParam("id") String id) throws IOException {
        return stockService.getStockInfo(id);
    }

    @RequestMapping("/star")
    @ApiOperation(value = "关注个股信息", httpMethod = "GET", notes = "关注个股信息")
    public StockInfo star(@RequestParam("id") String id) throws IOException {
        return stockService.star(id);
    }

    @RequestMapping("/delete")
    @ApiOperation(value = "删除个股信息", httpMethod = "GET", notes = "删除个股信息")
    public void delete(@RequestParam("id") String id) throws IOException {
        stockService.deleteStockInfo(id);
    }

    @RequestMapping("/getStockHistory")
    @ApiOperation(value = "获取个股历史信息", httpMethod = "GET", notes = "获取个股历史信息")
    public List<StockRecord> getStockHistory(@RequestParam("code") String code, @ApiParam(name = "start", value = "格式为yyyy-MM-dd") @RequestParam("start") String start, @ApiParam(name = "end", value = "格式为yyyy-MM-dd") @RequestParam("end") String end) throws Exception {
        return stockService.getStockHistory(code, start, end);
    }

    @RequestMapping("/getHigh")
    @ApiOperation(value = "获取个股历史信息中的最高点", httpMethod = "GET", notes = "获取个股历史信息中的最高点")
    public StockRecord getHigh(@RequestParam("code") String code, @ApiParam(name = "start", value = "格式为yyyy-MM-dd") @RequestParam(value = "start", required = false) String start, @ApiParam(name = "end", value = "格式为yyyy-MM-dd") @RequestParam(value = "end", required = false) String end) throws Exception {
        return stockService.getHigh(code, start, end);
    }

    @RequestMapping("/getLow")
    @ApiOperation(value = "获取个股历史信息中的最低点", httpMethod = "GET", notes = "获取个股历史信息中的最低点")
    public StockRecord getLow(@RequestParam("code") String code, @ApiParam(name = "start", value = "格式为yyyy-MM-dd") @RequestParam(value = "start", required = false) String start, @ApiParam(name = "end", value = "格式为yyyy-MM-dd") @RequestParam(value = "end", required = false) String end) throws Exception {
        return stockService.getLow(code, start, end);
    }

    @RequestMapping("/getLast")
    @ApiOperation(value = "获取个股历史信息中的最近时间", httpMethod = "GET", notes = "获取个股历史信息中的最近时间")
    public StockRecord getLastHistoryDate(@RequestParam("code") String code) throws Exception {
        return stockService.getLastHistoryDate(code);
    }


    @RequestMapping("/paginationQuery")
    @ApiOperation(value = "分页查询股票信息", httpMethod = "POST", notes = "分页查询股票信息")
    public PageInfo<StockInfo> paginationQuery(@ApiParam(value = "股票信息") @RequestBody StockInfo stockInfo, @RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize) throws Exception {
        return stockService.paginationQuery(stockInfo, pageNo, pageSize);
    }

    @RequestMapping("/query")
    @ApiOperation(value = "查询股票信息", httpMethod = "POST", notes = "查询股票信息")
    public List<StockInfo> query(@ApiParam(value = "股票信息") @RequestBody StockInfo stockInfo) throws Exception {
        return stockService.query(stockInfo);
    }

    @RequestMapping("/recommend")
    @ApiOperation(value = "推荐股票信息", httpMethod = "POST", notes = "推荐股票信息")
    public List<RecommendStockInfo> recommend(@ApiParam(value = "股票信息") @RequestBody StockInfo stockInfo, @ApiParam(name = "start", value = "格式为yyyy-MM-dd") @RequestParam(value = "start", required = false) String start, @ApiParam(name = "end", value = "格式为yyyy-MM-dd") @RequestParam(value = "end", required = false) String end) throws Exception {
        return stockService.recommend(stockInfo,start,end);
    }

    @RequestMapping("/unrecommended")
    @ApiOperation(value = "最不推荐股票信息", httpMethod = "POST", notes = "最不推荐股票信息")
    public List<RecommendStockInfo> unrecommended(@ApiParam(value = "股票信息") @RequestBody StockInfo stockInfo, @ApiParam(name = "start", value = "格式为yyyy-MM-dd") @RequestParam(value = "start", required = false) String start, @ApiParam(name = "end", value = "格式为yyyy-MM-dd") @RequestParam(value = "end", required = false) String end) throws Exception {
        return stockService.unrecommended(stockInfo,start,end);
    }

    @RequestMapping("/removeHistory")
    @ApiOperation(value = "删除个股历史信息", httpMethod = "GET", notes = "删除个股历史信息")
    public void removeHistory(@RequestParam("code") String code) throws Exception {
         stockService.removeHistory(code);
    }
}
