# YNAP Shop Technical Exercise
You have been given the outline of an e-Commerce application. We would like you to finish the implementation of the `YNAPShop` class to provide basic shopping features (as described in the scenarios below).

## Running the Application
The application should be exercised using the JUnit test class `YNAPShopTest`.  The tests can be run by clicking the *Run Unit Tests* button in HackerRank or, if you download the code and develop locally, with the maven command:

````mvn clean test````

Data to drive the application and pass the tests has been provided in `src/main/resources/product-data.csv`.

We would like you to finish the application by completing the following scenarios.

## Scenarios
### Exercise 1 - Loading and Listing Products
We have provided a product information data file. In exercise 1 we would like you to:

a) Implement the *loadProducts* method to parse the `product-data.csv` file.

b) Implement the *getProducts* method to return a list of products.

c) Implement the *displayProducts* method to return a String for displaying the products to a user (format this however you think best).

### Exercise 2 - Basket Functionality
Exercise 2 adds basket functionality to your applications. We would like you to:

a) Implement the *addProductToBasket* and *getBasketItems* methods to add a product to, and list items in, the basket.

b) Implement the *removeProductFromBasket* method to remove a product from the basket.

c) Implement the *getTotal* method to get the total value of the basket.

## Notes
You have total freedom to use any libraries to help complete the exercises, just update the dependencies in `pom.xml`. Add any classes and tests you think required.
