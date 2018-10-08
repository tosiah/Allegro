package pages;

import config.TestConfig;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.allure.annotations.Step;
import utility.Actions;

import java.util.List;

public class Cart extends BasePage {

    @FindBy(css = ".seller-items.ng-scope")
    private List<WebElement> products;

    private static By incrementBtnSelector = By.cssSelector("button[name='increment']");
    private static By decrementBtnSelector = By.cssSelector("button[name='decrement']");

    @FindBy(css = ".item .cart__offer-row .m-choice.m-choice--checkbox.ng-pristine")
    private List<WebElement> checkBoxes;

    @FindBy(css = ".cart-wrapper a")
    private WebElement cartIcon;

    @FindBy(css = ".clear-cart.btn-group .btn-group.base .btn")
    private WebElement clearBtn;

    @FindBy(css = "#removeModal .modal-footer .success-action button")
    private WebElement confirmClearBtn;

    @FindBy(css = ".custom-m-padding-right-0 svg")
    private WebElement clearBtnDropDown;

    @FindBy(css = ".m-dropdown__drop.delete-menu-drop-right button")
    private List<WebElement> deleteOptions;

    @FindBy(css = ".m-modal__content.ng-scope")
    private WebElement confirmationPopUp;

    @FindBy(css = ".m-modal__content.ng-scope .m-button.m-button--primary")
    private WebElement confirmClearSelectedBtn;

    @FindBy(css = ".m-choice.m-choice--checkbox")
    private WebElement checkBoxAll;

    //@FindBy(css = ".m-input-group .m-input.m-input--number")
    //private WebElement indexNumberOfParticularProduct;
    private static By numberOfProductInCartSelector = By.cssSelector("input[type='hidden']");
    //.m-input-group .m-input.m-input--number input[type='number']

    //@FindBy(css = ".m-number-picker__description.m-type")
    //private WebElement indexNumberOfAvailableAmount;
    private static By numberOfAvailableAmountOfProductSelector = By.cssSelector(".m-number-picker__description.m-type");



    public Cart() {
        super();
    }

    @Step
    public Cart goToCartViaIcon() {
        cartIcon.click();
        Assert.assertTrue("Going to cart didn't succeed", Actions.url().equals("https://allegro.pl/cart"));
        return new Cart();
    }

    @Step
    public void removeSelectedProducts() throws InterruptedException{
        int numberOfProductsInCart = this.checkIndexOfProductsInCart();
        System.out.println(numberOfProductsInCart);
        chooseRemoveSelectedOption();
        int numberOfProductsInCartAfterRemoval = checkIndexOfProductsInCart();
        System.out.println(numberOfProductsInCartAfterRemoval);
        Assert.assertTrue("Removing product didn't succeed", numberOfProductsInCartAfterRemoval == (numberOfProductsInCart - 1));
    }

    @Step
    public void removeAllProducts() throws InterruptedException{
        chooseRemoveAllOption();
        Assert.assertTrue("Removing product didn't succeed", checkIndexOfProductsInCart() == 0);
    }

    public void chooseRemoveSelectedOption() {
        Actions.scrollToShowElement(clearBtnDropDown);
        clearBtnDropDown.click();
        deleteOptions.get(1).click();
        confirmClearSelectedBtn.click();
    }

    public void chooseRemoveAllOption() {
        Actions.scrollToShowElement(clearBtnDropDown);
        clearBtnDropDown.click();
        deleteOptions.get(0).click();
        confirmClearSelectedBtn.click();
    }

    public Cart selectAll() {
        if (!checkBoxAll.isSelected()) {
            checkBoxAll.click();
        }
        assert checkBoxAll.isSelected() : "Check box 'Select All' is unselected";
        return this;
    }

    @Step
    public Cart unselectAll() {
        if (checkBoxAll.isSelected()) {
            checkBoxAll.click();
        }
        assert !checkBoxAll.isSelected() : "Check box 'Select All' is selected";
        return this;
    }

    @Step
    public Cart selectCheckBox(int indexOfCheckBox) {
        WebElement checkBox = findProductToSelect(indexOfCheckBox);
        checkBox.click();
        Assert.assertTrue("Selecting checkbox not successful", checkBox.isSelected());
        return this;
    }

    public WebElement findProductToSelect(int indexOfCheckBox) {
        WebElement element = null;
        if (indexOfCheckBox < checkBoxes.size()) {
            element = checkBoxes.get(indexOfCheckBox);
        }
        assert (element != null && !element.isSelected()) : "Product is already selected";
        Actions.scrollToShowElement(element);
        return element;
    }

    public Cart incrementProduct(int productIndex) throws InterruptedException {
        int indexOfProductsInCart = checkIndexOfProductsInCart();
        System.out.println(indexOfProductsInCart);
        WebElement productEl = chooseElement(productIndex);
        if(isIncrementPossible(productEl)){
            WebElement incrementBtn = productEl.findElement(incrementBtnSelector);
            assert incrementBtn.isEnabled() : "Increment button is disabled";
            incrementBtn.click();
            int indexOfProductsInCartAfterIncrement = checkIndexOfProductsInCart();
            System.out.println(indexOfProductsInCartAfterIncrement);
            assert indexOfProductsInCartAfterIncrement == indexOfProductsInCart + 1 : "Wrong index of products on cart icon";
        }
        else {
            System.out.println("There is not enough products in the store to increase amount of products");
        }
        return this;
    }

    public Cart decrementProduct(int productIndex) throws InterruptedException {
        int indexOfProductsInCart = checkIndexOfProductsInCart();
        WebElement productEl = chooseElement(productIndex);
        if(isDecrementPossible(productEl)){
            WebElement decrementBtn = productEl.findElement(decrementBtnSelector);
            assert decrementBtn.isEnabled() : "Decrement button is disabled";
            decrementBtn.click();
            int indexOfProductsInCartAfterDecrement = checkIndexOfProductsInCart();
            assert indexOfProductsInCartAfterDecrement == indexOfProductsInCart - 1 : "Wrong index of products on cart icon";
        }
        else {
            System.out.println("There is only one product of this kind in the cart. Decrement is not possible");
        }
        return this;
    }

    public Cart decrementProductButtonShouldBeDisabled(int productIndex) {
        WebElement productEl = chooseElement(productIndex);
        assert (!isDecrementPossible(productEl) & !productEl.findElement(decrementBtnSelector).isEnabled()) :
            "There is more than one product of this kind in the cart";
        return this;
    }

    public WebElement chooseElement(int productIndex) {
        assert (productIndex < products.size());
        WebElement productEl = products.get(productIndex);
        Actions.scrollToShowElement(productEl);
        return productEl;
    }

    public boolean isIncrementPossible(WebElement element){
        boolean isIncrementPossible = false;
        int availableProductAmount = Integer.parseInt((element.findElement(numberOfAvailableAmountOfProductSelector).getText()).replaceAll("[\\D]", ""));
        if(availableProductAmount>0){
            isIncrementPossible = true;
        }
        return isIncrementPossible;
    }

    public boolean isDecrementPossible(WebElement element){
        boolean isDecrementPossible = false;
        int particularProductAmountInCart = Integer.parseInt(element.findElement(numberOfProductInCartSelector).getAttribute("value"));
        if(particularProductAmountInCart>1){
            isDecrementPossible = true;
        }
        return isDecrementPossible;
    }

}