package com.stock.entrypoint;

import com.stock.converter.StockAppConverter;
import com.stock.entity.Stock;
import com.stock.entrypoint.data.request.StockRequest;
import com.stock.usecase.StockUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/stock")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockController {
    private final StockUseCase stockUseCase;
    private final StockAppConverter stockAppConverter;

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody StockRequest request) {
        Stock stockForDomain = this.stockAppConverter.toStockDomain(request);
        Stock stock = this.stockUseCase.create(stockForDomain);
        return new ResponseEntity<>(stock, CREATED);
    }

    @GetMapping("all")
    ResponseEntity<?> listAll() {
        List<Stock> stocks = this.stockUseCase.listAll();
        return new ResponseEntity<>(stocks, OK);
    }

    @GetMapping(path = "{id}")
    ResponseEntity<?> findById(@PathVariable int id) {
        Stock stock = this.stockUseCase.findById(id);
        return new ResponseEntity<>(stock, OK);
    }

    @PutMapping(path = "{id}")
    ResponseEntity<?> update(@PathVariable int id,  @Valid @RequestBody StockRequest request) {
        Stock stock = this.stockAppConverter.toStockDomain(request);
        return new ResponseEntity<>(this.stockUseCase.update(stock.withId(id)), OK);
    }

    @DeleteMapping(path = "{id}")
    ResponseEntity<?> delete(@PathVariable int id) {
        this.stockUseCase.delete(id);
        return new ResponseEntity<>(true, NO_CONTENT);
    }
}