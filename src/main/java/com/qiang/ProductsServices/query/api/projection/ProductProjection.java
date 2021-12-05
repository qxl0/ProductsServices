package com.qiang.ProductsServices.query.api.projection;

import com.qiang.ProductsServices.command.api.model.ProductRestModel;
import com.qiang.ProductsServices.data.Product;
import com.qiang.ProductsServices.data.ProductRespository;
import com.qiang.ProductsServices.query.api.queries.ProductQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {
    private ProductRespository productRespository;

    public ProductProjection(ProductRespository productRespository) {
        this.productRespository = productRespository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(ProductQuery productQuery){
        List<Product> products = productRespository.findAll();
        List<ProductRestModel> productRestModels =
                products.stream()
                .map(product -> ProductRestModel.builder()
                .quantity(product.getQuantity())
                .name(product.getName())
                .price(product.getPrice())
                .build())
                .collect(Collectors.toList());
        return productRestModels;
    }
}
