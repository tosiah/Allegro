package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;

public class SearchResultPage extends BasePage{

    @FindBy(className = "ff162b8")
    private List<WebElement> articles;

    private static By link = By.cssSelector("._8ce3910 a");

    @FindBy(css = "[itemprop=offers]")
    private WebElement productPageCheck;

    public SearchResultPage() {
        super();
    }

    @Step
    public ProductPage chooseLink(int linkIndex) {
        assert (linkIndex < articles.size()) : "The index is too high";
        WebElement linkEl = articles.get(linkIndex);
        linkEl.findElement(link).click();
        Assert.assertTrue("Going to product page didn't succeed",productPageCheck!=null);
        return new ProductPage();
    }
}
