package pages;

import config.TestConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import utility.Actions;

public class ProductPage extends BasePage{

    @FindBy(id = "add-to-cart-button")
    private WebElement addToCartBtn;

    @FindBy(id = "add-to-cart-si-precart")
    private WebElement goingToCartBtn;

    @FindBy(css = ".btn.btn-link")
    private WebElement continuingShoppingBtn;

    private static By continueOrGoToCartPopUp = By.cssSelector(".modal.info-modal.modal-precart.show");

    public ProductPage() {
        super();
    }

    @Step
    public Cart addAndGoToCart() throws InterruptedException{
        int numberOfProductsInCart = checkIndexOfProductsInCart();
        addToCart();
        goingToCartBtn.click();
        Assert.assertTrue("Going to cart didn't succeed", Actions.url().equals("https://allegro.pl/cart"));
        Assert.assertTrue("Wrong index of items in the cart", this.checkIndexOfProductsInCart() == (numberOfProductsInCart + 1));
        return new Cart();
    }

    @Step
    public ProductPage addAndContinueShopping() throws InterruptedException{
        int numberOfProductsInCart = checkIndexOfProductsInCart();
        addToCart();
        continuingShoppingBtn.click();
        Assert.assertTrue("Wrong index of items in the cart", this.checkIndexOfProductsInCart() == (numberOfProductsInCart + 1));
        return this;
    }

    public void addToCart(){
        addToCartBtn.click();
       // TestConfig.waitForElement(continueOrGoToCartPopUp);
        Actions.waitForElement(continueOrGoToCartPopUp);
    }

}
