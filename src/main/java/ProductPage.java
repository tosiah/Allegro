import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private static By addToCartBtn = By.id("add-to-cart");
    private static By goToCartBtn = By.id("add-to-cart-si-precart");
    private static By continueShoppingBtn = By.cssSelector(".country-PL.ssi.modal-open .modal.info-modal.modal-precart.show .modal-dialog .modal-content .modal-footer button");

    private WebDriver driver;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductPage addToCart() {
        WebElement addingToCartEl = driver.findElement(addToCartBtn);
        addingToCartEl.click();
        return this;
    }

    public Cart goToCart() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(goToCartBtn));
        WebElement goingToCartEl = driver.findElement(goToCartBtn);
        assert (goingToCartEl.isEnabled());
        goingToCartEl.click();
        return new Cart(driver);
    }

    public ProductPage continueShopping() throws InterruptedException {
        Thread.sleep(1000);
        WebElement continuingShoppingEl = driver.findElement(continueShoppingBtn);
        assert (continuingShoppingEl.isEnabled());
        continuingShoppingEl.click();
        return this;
    }
}
