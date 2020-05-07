package com.cml.eurder.api.controllers;

import com.cml.eurder.service.order.CreateOrderDto;
import com.cml.eurder.service.order.OrderDto;
import com.cml.eurder.service.order.OrderService;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(path = OrderController.ORDER_RESOURCE_PATH)
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    public static final String ORDER_RESOURCE_PATH = "/orders";
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasAuthority('VIEW_ALL_ORDERS')")
    @GetMapping(produces = "application/json")
    @ApiOperation(value = "Get all orders", notes = "A list of all orders will be returned", response = OrderDto.class)
    @ResponseStatus(HttpStatus.OK)
    public Collection<OrderDto> getAllOrders() {
        logger.info("Returning all orders");
        return orderService.getAllActiveOrders();
    }

    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Create order", notes = "A new order will be created", response = OrderDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody CreateOrderDto orderDto) {
        logger.info("Creating a new order");
        return orderService.addOrder(orderDto);
    }

    @PreAuthorize("hasAuthority('CREATE_ORDER')")
    @PostMapping(path = "/customer/{customer_id}/{order_id}", produces = "application/json")
    @ApiOperation(value = "Reorder an order", notes = "An order will be reordered", response = OrderDto.class)
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto reorder(@PathVariable("customer_id") long customerId, @PathVariable("order_id") long orderId) {
        logger.info("Reordering a previous order");
        return orderService.reorder(orderId, customerId);
    }

    @PreAuthorize("hasAuthority('VIEW_ALL_ORDERS')")
    @GetMapping(path = "/customer/{id}", produces = "application/json")
    @ApiOperation(value = "Get orders of a customer", notes = "A list of all orders of a customer will be returned", response = OrderDto.class)
    @ResponseStatus(HttpStatus.OK)
    public Collection<OrderDto> getOrdersOfACustomer(@PathVariable("id") long id) {
        logger.info("Returning all orders");
        return orderService.getOrdersOfACustomer(id);
    }

}
