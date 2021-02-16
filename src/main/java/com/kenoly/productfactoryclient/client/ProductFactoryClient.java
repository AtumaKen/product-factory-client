package com.kenoly.productfactoryclient.client;

import com.kenoly.productfactoryclient.model.CustomerDto;
import com.kenoly.productfactoryclient.model.ProductDto;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.UUID;


@ConfigurationProperties(prefix = "ken.factory", ignoreUnknownFields = false)
@Component
public class ProductFactoryClient {

    public final String PRODUCT_PATH_V1 = "/api/v1/product/";
    public final String CUSTOMER_PATH = "api/v1/customer/";
    private String apiHost;
    private final RestTemplate restTemplate;

    public ProductFactoryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public ProductDto getProductById(UUID uuid){
        return restTemplate.getForObject(apiHost + PRODUCT_PATH_V1 + uuid.toString(), ProductDto.class);
    }

    public URI saveNewProduct(ProductDto productDto){
        return restTemplate.postForLocation(apiHost + PRODUCT_PATH_V1, productDto);
    }

    public void updateProduct(UUID uuid, ProductDto productDto){
        restTemplate.put(apiHost + PRODUCT_PATH_V1 + "/" + uuid.toString(), productDto);
    }

    public void deleteBeer(UUID uuid){
        restTemplate.delete(apiHost + PRODUCT_PATH_V1 + "/" + uuid);
    }

    public CustomerDto getCustomerById(UUID uuid){
        return restTemplate.getForObject(apiHost + CUSTOMER_PATH + uuid.toString(), CustomerDto.class);
    }

    public URI saveNewCustomer(CustomerDto customerDto){
        return restTemplate.postForLocation(apiHost + CUSTOMER_PATH, customerDto );
    }

    public void updateCustomer(UUID uuid, CustomerDto customerDto){
        restTemplate.put(apiHost + CUSTOMER_PATH + "/" + uuid.toString(), customerDto);
    }

    public void deleteCustomer(UUID uuid){
        restTemplate.delete(apiHost + CUSTOMER_PATH + "/" + uuid);
    }


    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }
}
