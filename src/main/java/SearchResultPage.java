import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchResultPage {
    private static By article = By.className("fa72b28");
    private static By link = By.cssSelector("._433675f a");

    private WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public ProductPage chooseLink(int linkIndex) {
        List<WebElement> articleEls = driver.findElements(article);
        assert (linkIndex < articleEls.size()) : "The index is too high";
        WebElement linkEl = articleEls.get(linkIndex);
        linkEl.findElement(link).click();

        return new ProductPage(driver);
    }
}
