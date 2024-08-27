package org.uml.funfitness.pojo;

public class Product {
    private Integer productID;
    private String productName;
    private Integer productPrice;
    private Integer productQuantity;

    public Integer getProduct_id() {
        return productID;
    }

    public void setProduct_id(Integer product_id) {
        this.productID = product_id;
    }

    public String getProduct_name() {
        return productName;
    }

    public void setProduct_name(String product_name) {
        this.productName = product_name;
    }

    public Integer getProduct_price() {
        return productPrice;
    }

    public void setProduct_price(Integer product_price) {
        this.productPrice = product_price;
    }

    public Integer getProduct_quantity() {
        return productQuantity;
    }

    public void setProduct_quantity(Integer product_quantity) {
        this.productQuantity = product_quantity;
    }

    @Override
    public String toString() {
        return "Product [product_id=" + productID + ", product_name=" + productName
                + ", product_price=" + productPrice + ", product_quantity=" + productQuantity + "]";
    }
}
