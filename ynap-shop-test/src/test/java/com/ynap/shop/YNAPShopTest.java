package com.ynap.shop;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class YNAPShopTest {

    private YNAPShop shop;

    @Before
    public void setup() throws FileNotFoundException {
        this.shop = new YNAPShop();
        this.shop.loadProducts();
    }

    // Exercise 1a and 1b - Parsing the product-data.csv data file
    @Test
    public void getProducts() throws FileNotFoundException {
        assertThat("All products have been loaded.", shop.getProducts().size(), is(equalTo(7)));
    }

    // Exercise 1c - Displaying a list of the products
    @Test
    public void displayProducts() {
        assertThat("Display products is not null.", shop.displayProducts(), is(notNullValue()));
    }

    // Exercise 2a - Add products to a basket
    @Test
    public void addProductToBasket() {
        shop.addProductToBasket("5");
        shop.addProductToBasket("1");
        assertThat("Products have been added to a basket.", shop.getBasketItems().size(), is(equalTo(2)));
    }

    // Exercise 2b - Remove product from basekt
    @Test
    public void removeProductFromBasket() {
        shop.addProductToBasket("1");
        shop.addProductToBasket("5");
        shop.removeProductFromBasket("1");
        shop.addProductToBasket("7");
        assertThat("Products have been removed from a basket.", shop.getBasketItems().size(), is(equalTo(2)));
    }

    // Exercise 2c - Get total value of basket
    @Test
    public void getTotal() {
        shop.addProductToBasket("1");
        shop.addProductToBasket("2");
        shop.addProductToBasket("3");
        shop.addProductToBasket("4");
        shop.addProductToBasket("5");
        shop.addProductToBasket("6");
        shop.addProductToBasket("7");
        assertThat("Total value of basket has been calculated.", shop.getTotal(), is(equalTo(BigDecimal.valueOf(416.02))));
    }

}
