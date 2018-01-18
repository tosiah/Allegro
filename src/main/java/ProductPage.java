import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
    private static By addToCartButton = By.id("add-to-cart");
    private static By goToCartButton = By.id("add-to-cart-si-precart");
    private static By continueShoppingButton = By.cssSelector(".btn.btn-link");

    private WebDriver driver;

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }

    public ProductPage addToCart(){
        WebElement addingToCartEl = driver.findElement(addToCartButton);
        addingToCartEl.click();
        return this;
    }

    public Cart goToCart(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(goToCartButton));
        WebElement goingToCartEl = driver.findElement(goToCartButton);
        assert(goingToCartEl.isEnabled());
        goingToCartEl.click();
        return new Cart(driver);
    }

    public ProductPage continueShopping(){
        WebElement continuingShoppingEl = driver.findElement(continueShoppingButton);
        assert(continuingShoppingEl.isEnabled());
        continuingShoppingEl.click();
        return this;
    }
}
