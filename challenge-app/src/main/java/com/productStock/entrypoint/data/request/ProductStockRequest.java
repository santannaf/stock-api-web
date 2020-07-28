package com.productStock.entrypoint.data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductStockRequest {
    private int quantity;
    private int idStock;
    private int idProduct;
}