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
    private static By clearBtnDropDown = By.cssSelector(".clear-cart.btn-group .btn-group.base .btn.dropdown-toggle");
    private static By clearChosen = By.cssSelector(".clear-cart.btn-group .btn-group.base [href]");
    private static By confirmClearSelectedBtn = By.cssSelector("remove-selected-items-modal #removeModal .modal-footer .success-action button");
    private static By checkBoxAll = By.cssSelector("#item-select-all-checkbox");
    private static By deskForm = By.cssSelector("#goToDeskForm");
    private static By checkBox = By.cssSelector(".items-list .table-cell.item-select .item-select-checkbox.ng-pristine");

    private static WebDriver driver;

    public Cart(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement chooseElement(int productIndex) {
        List<WebElement> productEls = driver.findElements(product);
        assert (productIndex < productEls.size());
        WebElement productEl = productEls.get(productIndex);
        return productEl;
    }

    public WebElement incrementElement(int productIndex) {
        WebElement productEl = chooseElement(productIndex);
        WebElement incrementBtnEl = productEl.findElement(incrementBtn);
        assert (incrementBtnEl.isEnabled());
        return incrementBtnEl;
    }

    public WebElement decrementElement(int productIndex) {
        WebElement productEl = chooseElement(productIndex);
        WebElement decrementBtnEl = productEl.findElement(decrementBtn);
        assert (decrementBtnEl.isEnabled());
        return decrementBtnEl;
    }

    public void clearCart() {
        WebElement clearBtnEl = driver.findElement(clearBtn);
        clearBtnEl.click();
        WebElement confirmClearBtnEl = driver.findElement(confirmClearBtn);
        confirmClearBtnEl.click();
    }

    public void removeProductFromCart() {
        WebElement clearBtnDropDownEl = driver.findElement(clearBtnDropDown);
        clearBtnDropDownEl.click();
        WebElement clearChosenEl = driver.findElement(clearChosen);
        clearChosenEl.click();
        WebElement confirmClearSelectedBtnEl = driver.findElement(confirmClearSelectedBtn);
        confirmClearSelectedBtnEl.click();
    }

    public void selectAllCheckBox() {
        WebElement checkBoxAllEl = driver.findElement(checkBoxAll);
        if (!checkBoxAllEl.isSelected()) {
            checkBoxAllEl.click();
        }
        assert checkBoxAllEl.isSelected() : "Check box 'Select All' is unselected";
    }

    public void unselectAllCheckBox() {
        WebElement checkBoxAllEl = driver.findElement(checkBoxAll);
        if (checkBoxAllEl.isSelected()) {
            checkBoxAllEl.click();
        }
        assert !checkBoxAllEl.isSelected() : "Check box 'Select All' is selected";
    }

    public WebElement selectCheckBox(int indexOfCheckBox) {
        WebElement deskFormEl = driver.findElement(deskForm);
        List<WebElement> checkBoxEls = deskFormEl.findElements(checkBox);
        WebElement element = null;
        if (indexOfCheckBox < checkBoxEls.size()) {
            element = checkBoxEls.get(indexOfCheckBox);
        }
        assert (element != null && !element.isSelected());
        return element;
    }
}
