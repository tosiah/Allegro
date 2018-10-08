package config;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utility.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static config.WebDriverSingleton.getInstance;
import static config.WebDriverSingleton.quit;
import static utility.Screenshot.captureScreenshot;

public class TestConfig {

    private static WebDriver driver;

    public static By popupSelector = By.cssSelector("._3kk7b._vnd3k._1h8s6._13prn._12isx._kiiea._oeb1x");
    public static By okButtonSelector = By.cssSelector("._3kk7b._vnd3k._1h8s6._13prn._12isx._kiiea._oeb1x button");

    @Before
    public void setup() throws InterruptedException {
        driver = getInstance();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://allegro.pl");
        Actions.waitForElement(popupSelector);
        //Actions.waitForVisibilityOfElement(driver.findElement(popupSelector));
        //Actions.waitForClickabilityOfElement(driver.findElement(okButtonSelector)).click();
        driver.findElement(okButtonSelector).click();
    }

    @After
    public void tearDown() {
        captureScreenshot();
        quit();
    }





   /* public static void scrollToShowElement(WebElement element){
        driver = getInstance();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
        js.executeScript("window.scrollBy(0, -document.querySelector(arguments[0]).scrollHeight);", headerSelector);
    }*/

   /* public static void waitForElement(By elementSelector){
        driver = getInstance();
        Actions.waitForVisibilityOfElement(driver.findElement(elementSelector));
    }*/


    /*public static void waitForCartToLoad(){
        driver = getInstance();
       // List<WebElement> cartLoaders = new ArrayList<WebElement>();
       // WebElement cartLoader = driver.findElement(By.id("cartSpinner"));
       // WebElement cartSpinner = driver.findElement(By.cssSelector(".m-spinner.m-button__spinner"));
       // cartLoaders.add(cartLoader);
       // cartLoaders.add(cartSpinner);
        Actions.wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cartSpinner")));
        Actions.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("cartSpinner")));
        Actions.wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".m-spinner.m-button__spinner")));
    }*/

}
