package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static config.WebDriverSingleton.getInstance;

public class Actions {
    static final int WAIT_TIMEOUT = 10;
    public static WebDriverWait wait = new WebDriverWait(getInstance(), WAIT_TIMEOUT);
    public static String headerSelector = ".opbox-metrum-header__center";


    public static void waitForElement(By elementSelector){
        wait.until(ExpectedConditions.visibilityOf(getInstance().findElement(elementSelector)));
    }

    public static String url(){
        return getInstance().getCurrentUrl();
    }

    public static void scrollUp(){
        JavascriptExecutor js = (JavascriptExecutor) getInstance();
        js.executeScript("window.scrollTo(document.body.scrollHeight,0)");
    }

    public static void scrollToShowElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getInstance();
        js.executeScript("arguments[0].scrollIntoView();", element);
        js.executeScript("window.scrollBy(0, -document.querySelector(arguments[0]).scrollHeight);", headerSelector);
    }
}
