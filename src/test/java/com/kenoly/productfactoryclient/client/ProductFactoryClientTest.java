package com.kenoly.productfactoryclient.client;

import com.kenoly.productfactoryclient.model.CustomerDto;
import com.kenoly.productfactoryclient.model.ProductDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductFactoryClientTest {

    @Autowired
    ProductFactoryClient client;


    @Test
    void getProductById() {
        ProductDto dto = client.getProductById(UUID.randomUUID());
        assertNotNull(dto);
    }

    @Test
    void testSaveNewProduct(){
        ProductDto productDto = ProductDto.builder().productName("New Product").build();
        URI uri = client.saveNewProduct(productDto);
        assertNotNull(uri);
    }

    @Test
    void testUpdateProduct(){
        ProductDto productDto = ProductDto.builder().productName("New Product").build();
        client.updateProduct(UUID.randomUUID(), productDto);
    }

    @Test
    void testDeleteProduct() {
        client.deleteBeer(UUID.randomUUID());
    }

    @Test
    void getCustomerById() {
        var customerDto = client.getCustomerById(UUID.randomUUID());
        assertNotNull(customerDto);
    }

    @Test
    void saveNewCustomer() {
        var uri = client.saveNewCustomer(CustomerDto.builder().name("Someone").build());
        assertNotNull(uri);
    }

    @Test
    void updateCustomer() {
        client.updateCustomer(UUID.randomUUID(), CustomerDto.builder().name("Someone").build());
    }

    @Test
    void deleteCustomer() {
        client.deleteCustomer(UUID.randomUUID());
    }
}