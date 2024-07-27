package finos.traderx.referencedata.service;

import finos.traderx.referencedata.model.Stock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StockServiceTests {
    @Autowired
    private StockService stockService;

    @Test
    public void getAllStocks() {
        assertEquals(stockService.getAllStocks().size(),505);
    }

    @Test
    public void getStock() {
        assert(stockService.getStockByTicker("MMM").isPresent());
        assertEquals(stockService.getStockByTicker("MMM").get().getTicker(),"MMM");
        assert(stockService.getStockByTicker("MMM1").isEmpty());
    }
}
