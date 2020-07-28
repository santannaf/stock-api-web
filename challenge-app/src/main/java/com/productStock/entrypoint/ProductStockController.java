package com.productStock.entrypoint;

import com.productStock.converter.ProductStockAppConverter;
import com.productStock.entity.ProductStock;
import com.productStock.entrypoint.data.request.ProductStockRequest;
import com.productStock.usecase.ProductStokeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/product/stock")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductStockController {
    private final ProductStokeUseCase productStokeUseCase;
    private final ProductStockAppConverter productStockAppConverter;

    @PostMapping
    ResponseEntity<?> insertProductIntoStock(@Valid @RequestBody ProductStockRequest request) {
        ProductStock productStock = this.productStockAppConverter.toProductStockDomainByRequest(request);
        return new ResponseEntity<>(this.productStokeUseCase.insertProductAtStock(productStock), CREATED);
    }

    @PostMapping("load/{idProduct}")
    ResponseEntity<?> loadStocksWithProduct(@PathVariable int idProduct) {
        return new ResponseEntity<>(this.productStokeUseCase.loadStockWithProduct(idProduct), CREATED);
    }

    @GetMapping(path = "list")
    ResponseEntity<?> listAllProductsIntoStocks() {
        return new ResponseEntity<>(this.productStokeUseCase.listAllProductsInTheStocks(), OK);
    }

    @GetMapping(path = "list/customs")
    ResponseEntity<?> listAllProductsIntoStocksCustom() {
        return new ResponseEntity<>(this.productStokeUseCase.listAllProductsInTheStocksCustom(), OK);
    }
}