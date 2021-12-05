package com.qiang.ProductsServices.command.api.aggregate;

import com.qiang.ProductsServices.command.api.commands.CreateProductCommand;
import com.qiang.ProductsServices.command.api.events.ProductCreatedEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    public ProductAggregate(CreateProductCommand createProductCommand) {
        // validation
        // event --> publish
        ProductCreatedEvent productCreatedEvent =
                new ProductCreatedEvent();

        BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

        // publish

        AggregateLifecycle.apply(productCreatedEvent);
    }

    public ProductAggregate() {
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
       this.quantity = productCreatedEvent.getQuantity();
       this.productId = productCreatedEvent.getProductId();
       this.price = productCreatedEvent.getPrice();
       this.name = productCreatedEvent.getName();
    }
}
