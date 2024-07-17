package com.store.discount_engine.calculator;

import com.store.discount_engine.enums.CustomerType;
import com.store.discount_engine.model.Customer;
import com.store.discount_engine.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class DiscountCalculatorTest {

    @InjectMocks
    private DiscountCalculator discountCalculator;

    @Test
    void whenCustomerIsEmployeeThenDiscountIs30Test() throws Exception {
        Product product = new Product("prod1","book",100,1);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.EMPLOYEE);
        ReflectionTestUtils.setField(discountCalculator, "employeeDiscountPercentage", 30);
        Double discount = discountCalculator.calculateDiscountOnTotalAmount(100.0,customer);
        Assertions.assertEquals(30.0,discount);

    }
    @Test
    void whenCustomerIsAffiliateThenDiscountIs10Test() throws Exception {
        Product product = new Product("prod1","book",100,1);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.AFFILIATE);
        ReflectionTestUtils.setField(discountCalculator, "affiliateDiscountPercentage", 10);
        Double discount = discountCalculator.calculateDiscountOnTotalAmount(100.0,customer);
        Assertions.assertEquals(10.0,discount);

    }

    @Test
    void whenCustomerIsCustomerOver2YearsThenDiscountIs5Test() throws Exception {
        Product product = new Product("prod1","book",100,1);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.CUSTOMER);
        customer.setYearsWithStore(3);
        ReflectionTestUtils.setField(discountCalculator, "oldCustomerDiscountPercentage", 5);
        Double discount = discountCalculator.calculateDiscountOnTotalAmount(100.0,customer);
        Assertions.assertEquals(5.0,discount);

    }
    @Test
    void whenCustomerIsNewCustomerThenDiscountIs5Every100Test() throws Exception {
        Product product = new Product("prod1","book",200,1);
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        Customer customer = new Customer();
        customer.setCustomerType(CustomerType.CUSTOMER);
        customer.setYearsWithStore(0);
        ReflectionTestUtils.setField(discountCalculator, "newCustomerDiscountPercentage", 5);
        Double discount = discountCalculator.calculateDiscountOnTotalAmount(100.0,customer);
        Assertions.assertEquals(5.0,discount);

    }
}
