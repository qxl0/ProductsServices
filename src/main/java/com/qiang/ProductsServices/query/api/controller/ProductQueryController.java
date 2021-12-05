package com.qiang.ProductsServices.query.api.controller;

import com.qiang.ProductsServices.command.api.model.ProductRestModel;
import com.qiang.ProductsServices.query.api.queries.ProductQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {
    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getProducts(){
        ProductQuery queryProduct= new ProductQuery();

        List<ProductRestModel> productResModelList =
                queryGateway.query(queryProduct,
                        ResponseTypes.multipleInstancesOf(ProductRestModel.class))
                        .join();
        return productResModelList;
    }
}
