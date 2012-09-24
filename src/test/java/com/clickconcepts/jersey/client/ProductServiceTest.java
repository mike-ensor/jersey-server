package com.clickconcepts.jersey.client;

import com.clickconcepts.jersey.server.model.Product;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ProductServiceTest {

    private ProductService service;

    @Before
    public void setUp() {
        service = new ProductService();
    }

    @Test
    public void getProduct() {
        Product product = service.getProduct("21");
        assertThat(product.getId()).isPositive();
        assertThat(product.getDescription()).containsIgnoringCase("SOME DESCRIPTION");
    }

    @Test
    public void getProductWithUpdate() {
        Product product = service.getProduct("21", "mydesc");
        assertThat(product.getId()).isPositive();
        assertThat(product.getDescription()).doesNotContain("UPDATE").containsIgnoringCase("mydesc");
    }

    @Test
    public void getProductWithUpdate_updated() {
        Product product = service.getUpdatedProduct("21", "mydesc");
        assertThat(product.getId()).isPositive();
        assertThat(product.getDescription()).containsIgnoringCase("mydesc").contains("UPDATE");
    }

    @Test
    public void triggerException() {
        Product product = service.triggerException();
    }
}
