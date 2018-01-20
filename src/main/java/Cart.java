import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Cart {
    private static By product = By.cssSelector(".seller-items.ng-scope");
    private static By incrementBtn = By.className("btn-increment");
    private static By decrementBtn = By.className("btn-decrement");
    private static By clearBtn = By.cssSelector(".clear-cart.btn-group .btn-group.base .btn");
    private static By confirmClearBtn = By.cssSelector("#removeModal .modal-footer .success-action button");
    private static By checkBoxAll = By.cssSelector("#item-select-all-checkbox");

    private static WebDriver driver;

    public Cart(WebDriver driver){
        this.driver = driver;
    }

    public void selectAllCheckBox(){
        WebElement checkBoxAllEl = driver.findElement(checkBoxAll);
        if(!checkBoxAllEl.isSelected()){
            checkBoxAllEl.click();
        }
        assert checkBoxAllEl.isSelected() : "Check box 'Select All' is unselected";
    }

    public void unselectAllCheckBox(){
        WebElement checkBoxAllEl = driver.findElement(checkBoxAll);
        if(checkBoxAllEl.isSelected()){
            checkBoxAllEl.click();
        }
        assert !checkBoxAllEl.isSelected() : "Check box 'Select All' is selected";
    }

    public void clearCart(){
        WebElement clearBtnEl = driver.findElement(clearBtn);
        clearBtnEl.click();
        WebElement confirmClearBtnEl = driver.findElement(confirmClearBtn);
        confirmClearBtnEl.click();
    }

    public WebElement chooseElement(int productIndex){
        List<WebElement> productEls = driver.findElements(product);
        assert (productIndex<productEls.size());
        WebElement productEl = productEls.get(productIndex);
        return productEl;
    }

    public WebElement incrementElement(int productIndex){
        WebElement productEl = chooseElement(productIndex);
        WebElement incrementBtnEl = productEl.findElement(incrementBtn);
        assert(incrementBtnEl.isEnabled());
        return incrementBtnEl;
    }

    public WebElement decrementElement(int productIndex){
        WebElement productEl = chooseElement(productIndex);
        WebElement decrementBtnEl = productEl.findElement(decrementBtn);
        assert(decrementBtnEl.isEnabled());
        return decrementBtnEl;
    }
}
