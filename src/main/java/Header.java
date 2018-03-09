import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Header {
    private static By searchBar = By.className("metrum-search__query");
    private static By categories = By.cssSelector(".fee54_2mDvq.fee54_1SADD.fee54_3EtAX.fee54_2uFef.fee54_2mDvq a");
    private static By searchBtn = By.cssSelector(".metrum-search__submit._xepa8._1c5ga._1amu3");
    private static By options = By.cssSelector(".fee54_2rIHW.fee54_33vjU");
    private static By subMenuOptions = By.cssSelector(".fee54_1NJil.fee54_14dcF.fee54_3GPbu a");
    private static By cartIcon = By.className("metrum-cart-status");
    private static By indexOfProductsInCart = By.cssSelector(".cart-quantity.m-notification.m-notification--icon");

    private WebDriver driver;

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    public Cart goToCartViaIcon() {
        WebElement cartIconEl = driver.findElement(cartIcon);
        cartIconEl.click();
        return new Cart(driver);
    }

    public Integer checkIndexOfProductsInCart() throws InterruptedException {
        Thread.sleep(1000);
        WebElement indexEl = driver.findElement(indexOfProductsInCart);
        if (indexEl.getText().equals("")) {
            return 0;
        } else {
            return Integer.parseInt(indexEl.getText());
        }
    }


    public SearchResultPage searchProduct(String productName) {
        WebElement searchBarEl = driver.findElement(searchBar);
        searchBarEl.click();
        searchBarEl.sendKeys(productName);
        WebElement searchBtnEl = driver.findElement(searchBtn);
        searchBtnEl.click();
        return new SearchResultPage(driver);
    }

    public WebElement searchCategory(String categoryName) throws InterruptedException {
        Thread.sleep(1000);
        WebElement categoriesEl = driver.findElement(categories);
        categoriesEl.click();
        List<WebElement> optionsEls = driver.findElements(options);
        WebElement element = null;
        for (WebElement el : optionsEls) {
            if (el.getText().equals(categoryName)) {
                element = el;
                break;
            }
        }
        assert (element != null) : "There is no such category";
        return element;
    }

    public WebElement searchSubCategory(String subCategoryName) {
        List<WebElement> subMenuOpionsEls = driver.findElements(subMenuOptions);
        WebElement element = null;
        for (WebElement el : subMenuOpionsEls) {
            if (el.getText().equals(subCategoryName)) {
                element = el;
                break;
            }
        }
        assert (element != null) : "There is no such subcategory";
        return element;
    }
}
