package tests;

import config.TestConfig;
import org.junit.Test;
import pages.Cart;
import pages.SearchResultPage;

/**
 * Created by Antonina on 2018-01-18.
 */
public class CartTest extends TestConfig{

    @Test
    public void userShouldGetToSerchResultPage(){
        new SearchResultPage()
                .searchProductViaSearchBar("kocyk")
                .searchProductByCategory("Uroda", "Szampony");
    }

    @Test
    public void userShouldAddProductAndGoToCart() throws InterruptedException {
        new SearchResultPage()
                .searchProductByCategory("Uroda", "Szampony")
                .chooseLink(2)
                .addAndGoToCart();
    }

    @Test
    public void userShouldAddProductAndContinueShopping() throws InterruptedException {
        new SearchResultPage()
                .searchProductByCategory("Dziecko", "Klocki")
                .chooseLink(2)
                .addAndContinueShopping();
    }



    @Test
    public void userShouldRemoveSelectedProductsFromCart() throws InterruptedException {
        new SearchResultPage()
                .searchProductByCategory("Uroda", "Szampony")
                .chooseLink(2)
                .addAndContinueShopping()
                .searchProductByCategory("Dziecko", "Klocki")
                .chooseLink(1)
                .addAndGoToCart()
                .unselectAll()
                .selectCheckBox(1)
                .removeSelectedProducts();
    }

    @Test
    public void userShouldRemoveAllProductsFromCart() throws InterruptedException {
        new SearchResultPage()
                .searchProductByCategory("Uroda", "Szampony")
                .chooseLink(2)
                .addAndContinueShopping()
                .searchProductByCategory("Dziecko", "Klocki")
                .chooseLink(1)
                .addAndGoToCart()
                .unselectAll()
                .selectCheckBox(1)
                .removeAllProducts();
    }

    @Test
    public void userShouldSelectAllProductsFromCart() throws InterruptedException {
        new SearchResultPage()
                .searchProductByCategory("Uroda", "Szampony")
                .chooseLink(2)
                .addAndContinueShopping()
                .searchProductByCategory("Dziecko", "Klocki")
                .chooseLink(1)
                .addAndGoToCart()
                .unselectAll()
                .selectAll();
    }

    @Test
    public void incrementProductAmountInCartShouldBeSuccessful() throws InterruptedException {
        new SearchResultPage()
                .searchProductByCategory("Uroda", "Szampony")
                .chooseLink(2)
                .addAndGoToCart()
                .incrementProduct(0);
    }

    @Test
    public void decrementProductAmountInCartShouldBeSuccessful() throws InterruptedException {
        new SearchResultPage()
                .searchProductByCategory("Uroda", "Szampony")
                .chooseLink(2)
                .addAndGoToCart()
                .incrementProduct(0)
                .decrementProduct(0);
    }

    @Test
    public void decrementProductAmountInCartShouldFail() throws InterruptedException {
        new SearchResultPage()
                .searchProductByCategory("Uroda", "Szampony")
                .chooseLink(2)
                .addAndGoToCart()
                .decrementProductButtonShouldBeDisabled(0);
    }


    @Test
    public void userShouldGoToCartViaIcon(){
        new Cart()
                .goToCartViaIcon();
    }



}
