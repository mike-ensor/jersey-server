package com.clickconcepts.jersey.client;

import com.clickconcepts.jersey.server.model.MyException;
import com.clickconcepts.jersey.server.model.Product;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import javax.ws.rs.core.MediaType;

public class ProductService {

    private WebResource resource;
    private Client client;

    public ProductService() {
        ClientConfig cc = new DefaultClientConfig();
        cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
        cc.getProperties().put(ClientConfig.PROPERTY_BUFFER_RESPONSE_ENTITY_ON_EXCEPTION, true);

        client = Client.create(cc);
        resource = client.resource("http://localhost:8080/server").path("product");
    }

    public Product getProduct(String id) {
        return resource
                .path(id)
                .accept(MediaType.APPLICATION_JSON)
                .get(Product.class);
    }

    public Product getProduct(String id, String description) {
        return resource
                .path(id).path(description)
                .accept(MediaType.APPLICATION_JSON)
                .get(Product.class);
    }

    public Product getUpdatedProduct(String id, String description) {
        Product updatingProduct = new Product();
        updatingProduct.setId(Long.parseLong(id));
        updatingProduct.setDescription(description);

        ClientResponse response = resource
                .path("response")
                .accept(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, updatingProduct);

        if (response.getClientResponseStatus() != ClientResponse.Status.OK) {
            System.out.println("Exception!!!");
        }

        return response.getEntity(Product.class);
    }

    public Product triggerException() {

        ClientResponse response = resource
                .path("exception")
                .accept(MediaType.APPLICATION_JSON)
                .type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        if (response.getClientResponseStatus() != ClientResponse.Status.OK) {
            MyException entity = response.getEntity(MyException.class);
            System.out.println("\n\n\n\n\n\n========================\n" + entity.getMessageCode() + "\n========================\n\n\n\n\n\n");
            return null;
        }

        return response.getEntity(Product.class);
    }
}
