package com.store.discount_engine.controller;


import com.store.discount_engine.enums.CustomerType;
import com.store.discount_engine.exception.BadRequestException;
import com.store.discount_engine.model.Customer;
import com.store.discount_engine.model.Product;
import com.store.discount_engine.request.Order;
import com.store.discount_engine.response.DiscountResponse;
import com.store.discount_engine.service.impl.DiscountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class DiscountControllerTest {

    @InjectMocks
    private DiscountController discountController;

    @Mock
    private DiscountServiceImpl discountService;

    @Test
    public void testCalculateNetAmount() throws Exception{
        Order order = new Order();
        DiscountResponse discountResponse = new DiscountResponse();
        discountResponse.setNetAmount(100.0);
        Mockito.when(discountService.calculateNetAmount(order)).thenReturn(discountResponse);
        Assertions.assertEquals(100.0,discountController.calculateNetAmount(order).getNetAmount());
    }

    @Test
    public void testCalculateNetAmountThrowsException() throws Exception{
        Order order = new Order();
        Mockito.when(discountService.calculateNetAmount(order)).thenThrow(BadRequestException.class);
        assertThrows(BadRequestException.class,()->discountService.calculateNetAmount(order));
    }
}
