package com.stock.entrypoint.data.request;

import com.product.entrypoint.data.request.ProductRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemStockRequest {
    private ProductRequest product;
    private int quantity;
}