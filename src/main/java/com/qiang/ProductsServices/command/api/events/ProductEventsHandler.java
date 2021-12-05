package com.qiang.ProductsServices.command.api.events;

import com.qiang.ProductsServices.data.Product;
import com.qiang.ProductsServices.data.ProductRespository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ProductEventsHandler {
    private ProductRespository productRespository;

    public ProductEventsHandler(ProductRespository productRespository) {
        this.productRespository = productRespository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event){
        Product product = new Product();

        BeanUtils.copyProperties(event, product);
        productRespository.save(product);
    }
}
