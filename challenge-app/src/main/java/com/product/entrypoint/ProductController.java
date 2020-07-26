package com.product.entrypoint;

import com.product.converter.ProductAppConverter;
import com.product.entity.Product;
import com.product.entrypoint.data.request.ProductRequest;
import com.product.usecase.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductController {
    private final ProductUseCase productUseCase;
    private final ProductAppConverter productAppConverter;

    @PostMapping
    ResponseEntity<?> create(@Valid @RequestBody ProductRequest request) {
        Product product = this.productAppConverter.toProductDomain(request);
        return new ResponseEntity<>(this.productUseCase.create(product), CREATED);
    }

    @GetMapping
    ResponseEntity<?> listAll() {
        return new ResponseEntity<>(this.productUseCase.listAll(), OK);
    }
}