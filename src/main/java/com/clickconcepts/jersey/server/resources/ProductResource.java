package com.clickconcepts.jersey.server.resources;

import com.clickconcepts.jersey.server.model.MyException;
import com.clickconcepts.jersey.server.model.Product;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;


//URI uri = uriBuilder.build(product.getId(), "{quantity}", "{username}");


@Resource
@Path("/product")
public class ProductResource {

    private static final UriBuilder uriBuilder = UriBuilder.fromPath("resource/product").path("{id}/stock/{quantity}/{username}");


    @GET
    @Path("/{id}")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Product getProduct(@PathParam("id") String id) {
        return getMockedProduct(id);
    }

    @GET
    @Path("/{id}/{description}")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Product getProductWithDescription(@PathParam("id") String id, @PathParam("description") String description) {
        return getMockedProduct(id, description);
    }

    @POST
    @Path("/submit")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Product getProductWithDescriptionPost(Product product) {
        String oldDesc = product.getDescription();
        product.setDescription(oldDesc + " UPDATED");
        return product;
    }

    @GET
    @Path("/exception")
    @Produces(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes(value = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Product throwSomeSortOfException() {
        throw new MyException(123L, "My Overriden Message");
    }

    private Product getMockedProduct(String id) {
        return getMockedProduct(id, getMockedDescription(Long.parseLong(id)));
    }

    private Product getMockedProduct(String id, String description) {
        Product product = new Product();
        product.setId(Long.parseLong(id));
        product.setDescription(description);
        return product;
    }

    private String getMockedDescription(long id) {
        return "SOME DESCRIPTION of product# " + id;
    }

}
