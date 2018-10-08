package pages;

/**
 * Created by Antonina on 2018-10-02.
 */

import static config.WebDriverSingleton.getInstance;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;
import config.TestConfig;
import ru.yandex.qatools.allure.annotations.Step;

public class BasePage {

    Actions actions = new Actions(getInstance());

    @FindBy(css = "._26e29_2IhX3.opbox-sheet._26e29_2fPDv.card")
    private WebElement pageContent;

    private static By pageContentSelector = By.cssSelector("._26e29_2IhX3.opbox-sheet._26e29_2fPDv.card");

    @FindBy(css = "._1caf7.fee54_36PLn.fee54_1Uwxm a")
    private WebElement showCategoriesBtn;

    @FindBy(css = "._r5ckd._882d6_cQRcd a")
    private List<WebElement> options;

    @FindBy(css = "._2508c_2Fw19._3a4zn._12vfa a")
    private List<WebElement> subMenuOptions;

    @FindBy(className = "metrum-search__query")
    private WebElement searchBar;

    @FindBy(css = ".metrum-search__submit._xepa8._1c5ga._1amu3")
    private WebElement searchBtn;

    @FindBy(className = "ff162b8")
    private WebElement searchResultPageCheck;

    @FindBy(css = ".cart-quantity.m-notification.m-notification--icon")
    private WebElement indexOfProductsInCart;


    public BasePage() {
        PageFactory.initElements(getInstance(), this);
        utility.Actions.waitForElement(pageContentSelector);
    }

    @Step
    public SearchResultPage searchProductViaSearchBar(String productName) {
        searchBar.click();
        searchBar.sendKeys(productName);
        searchBtn.click();
        Assert.assertTrue("Search result page didn't occur", searchResultPageCheck!=null);
        return new SearchResultPage();
    }

    @Step
    public SearchResultPage searchProductByCategory(String categoryName, String subCategoryName){
        WebElement categoryNameEl = this.searchCategory(categoryName);
        actions.moveToElement(categoryNameEl).build().perform();
        WebElement subCategoryNameEl = this.searchSubCategory(subCategoryName);
        actions.moveToElement(subCategoryNameEl).click().build().perform();
        return new SearchResultPage();
    }

    public WebElement searchCategory(String categoryName){
        utility.Actions.scrollUp();
        showCategoriesBtn.click();
        WebElement element = null;
        for (WebElement el : options) {
            if (el.getText().equals(categoryName)) {
                element = el;
                break;
            }
        }
        assert (element != null) : "There is no such category";
        return element;
    }

    public WebElement searchSubCategory(String subCategoryName) {
        WebElement element = null;
        for (WebElement el : subMenuOptions) {
            if (el.getText().equals(subCategoryName)) {
                element = el;
                break;
            }
        }
        assert (element != null) : "There is no such subcategory";
        return element;
    }

    public Integer checkIndexOfProductsInCart() throws InterruptedException {
        Thread.sleep(3000);
        //TestConfig.waitForCartToLoad();
        if (indexOfProductsInCart.getText().equals("")) {
            return 0;
        }
        return Integer.parseInt(indexOfProductsInCart.getText());
    }
}