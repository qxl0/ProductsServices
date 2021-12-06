package com.qiang.ProductsServices.command.api.events;

import com.qiang.ProductsServices.data.Product;
import com.qiang.ProductsServices.data.ProductRespository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {
    private ProductRespository productRespository;

    public ProductEventsHandler(ProductRespository productRespository) {
        this.productRespository = productRespository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) throws Exception {
        Product product = new Product();

        BeanUtils.copyProperties(event, product);
        productRespository.save(product);
        //throw new Exception("error occured");
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
