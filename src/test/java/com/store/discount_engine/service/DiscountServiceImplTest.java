package com.store.discount_engine.service;

import com.store.discount_engine.constants.Constants;
import com.store.discount_engine.calculator.DiscountCalculator;
import com.store.discount_engine.enums.CustomerType;
import com.store.discount_engine.exception.BadRequestException;
import com.store.discount_engine.model.Customer;
import com.store.discount_engine.model.Product;
import com.store.discount_engine.request.Order;
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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class DiscountServiceImplTest {
    @InjectMocks
    private DiscountServiceImpl discountService;

    @Mock
    private DiscountCalculator discountCalculator;

    @Test
    void whenGroceryItemsAreIncludedThenNoDiscountTest() throws Exception {
        List<Product> products = createMockProducts("grocery");
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.EMPLOYEE);
        Order order = createMockOrder(products, customer);
        ReflectionTestUtils.setField(discountService, "nonDiscountedProducts", "grocery");
        Mockito.when(discountCalculator.calculateDiscountOnTotalAmount(Mockito.anyDouble(),Mockito.any())).thenReturn(0.0);
        Double netAmount = discountService.calculateNetAmount(order).getNetAmount();
        Assertions.assertEquals(100.0,netAmount);
    }

    @Test
    void whenNonGroceryItemsAreIncludedThenAppliedDiscountTest() throws Exception {
        List<Product> products = createMockProducts("book");
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.EMPLOYEE);
        Order order = createMockOrder(products, customer);
        ReflectionTestUtils.setField(discountService, "nonDiscountedProducts", "grocery");
        Mockito.when(discountCalculator.calculateDiscountOnTotalAmount(Mockito.anyDouble(),Mockito.any())).thenReturn(30.0);
        Double netAmount = discountService.calculateNetAmount(order).getNetAmount();
        Assertions.assertEquals(70.0,netAmount);
    }

    @Test
    void whenOrderIsNullThenThrowExceptionTest() throws Exception {
        Exception exception = assertThrows(BadRequestException.class,()->discountService.calculateNetAmount(null));
        Assertions.assertEquals(Constants.REQUEST_OBJECT_NULL,exception.getMessage());
    }

    @Test
    void whenProductIsNullThenThrowExceptionTest() throws Exception {
        Order order = new Order();
        Exception exception = assertThrows(BadRequestException.class,()->discountService.calculateNetAmount(order));
        Assertions.assertEquals(Constants.PRODUCT_LIST_IS_NULL,exception.getMessage());
    }

    @Test
    void whenCustomerIsNullThenThrowExceptionTest() throws Exception {
        Order order = new Order();
        order.setProducts(createMockProducts("book"));
        Exception exception = assertThrows(BadRequestException.class,()->discountService.calculateNetAmount(order));
        Assertions.assertEquals(Constants.CUSTOMER_OBJECT_NULL,exception.getMessage());
    }

    @Test
    void whenProductNameIsNullThenThrowExceptionTest() throws Exception {
        Order order = new Order();
        Product product = new Product();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Customer customer = createMockCustomer(CustomerType.EMPLOYEE);
        order.setCustomer(customer);
        order.setProducts(productList);
        Exception exception = assertThrows(BadRequestException.class,()->discountService.calculateNetAmount(order));
        Assertions.assertEquals(Constants.PRODUCT_NAME_INVALID,exception.getMessage());
    }

    @Test
    void whenProductCategoryIsInvalidThenThrowExceptionTest() throws Exception {
        Order order = new Order();
        Product product = new Product();
        product.setName("Product1");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        order.setProducts(productList);
        Customer customer = createMockCustomer(CustomerType.EMPLOYEE);
        order.setCustomer(customer);
        Exception exception = assertThrows(BadRequestException.class,()->discountService.calculateNetAmount(order));
        Assertions.assertEquals(Constants.PRODUCT_CATEGORY_INVALID+product.getName(),exception.getMessage());
    }

    @Test
    void whenProductPriceIsInvalidThenThrowExceptionTest() throws Exception {
        Order order = new Order();
        Product product = new Product();
        product.setName("Product1");
        product.setCategory("book");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        order.setProducts(productList);
        Customer customer = createMockCustomer(CustomerType.EMPLOYEE);
        order.setCustomer(customer);
        Exception exception = assertThrows(BadRequestException.class,()->discountService.calculateNetAmount(order));
        Assertions.assertEquals(Constants.PRODUCT_PRICE_INVALID+product.getName(),exception.getMessage());
    }

    @Test
    void whenProductQuantityIsInvalidThenThrowExceptionTest() throws Exception {
        Order order = new Order();
        Product product = new Product();
        product.setName("Product1");
        product.setCategory("book");
        product.setPrice(100.0);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        order.setProducts(productList);
        Customer customer = createMockCustomer(CustomerType.EMPLOYEE);
        order.setCustomer(customer);
        Exception exception = assertThrows(BadRequestException.class,()->discountService.calculateNetAmount(order));
        Assertions.assertEquals(Constants.PRODUCT_QUANTITY_INVALID+product.getName(),exception.getMessage());
    }

    private Order createMockOrder(List<Product> products, Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setProducts(products);
        return order;
    }

    private Customer createMockCustomer(CustomerType customerType) {
        Customer customer = new Customer();
        customer.setCustomerType(customerType);
        return customer;
    }

    private List<Product> createMockProducts(String category){
        Product product = new Product("prod1",category,100,1);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        return productList;
    }

}
