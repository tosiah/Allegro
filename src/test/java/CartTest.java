import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Created by Antonina on 2018-01-18.
 */
public class CartTest {
    public static WebDriver driver;

    private void clearCart() throws InterruptedException {
        Header header = new Header(driver);
        Cart cart = header.goToCartViaIcon();
        cart.clearCart();
        Assert.assertEquals((Integer) 0, header.checkIndexOfProductsInCart());
    }

    private Cart removeProduct(){
        Cart cart = new Cart(driver);
        Actions actions = new Actions(driver);
        WebElement removeBtn = cart.removeProduct();
        if(removeBtn.isEnabled()) {
            actions.moveToElement(removeBtn).click().build().perform();
        }
        return new Cart(driver);
    }

    private SearchResultPage searchProductByCategory(String categoryName, String subCategoryName){
        Header header = new Header(driver);
        WebElement categoryNameEl = header.searchCategory(categoryName);
        Actions actions = new Actions(driver);
        actions.moveToElement(categoryNameEl).build().perform();
        WebElement subCategoryNameEl = header.searchSubCategory(subCategoryName);
        actions.moveToElement(subCategoryNameEl).click().build().perform();
        return new SearchResultPage(driver);
    }

    private void selectCheckBox(int indexOfCheckBox){
        Cart cart = new Cart(driver);
        cart.selectCheckBox(indexOfCheckBox).click();
    }

    @BeforeClass
    public static void setup() {
        String exePath = "D:\\drivers\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://allegro.pl");
    }

    @Before
    public void beforeTest() throws InterruptedException {

        Header header = new Header(driver);
        if (!header.checkIndexOfProductsInCart().equals(0)) {
            this.clearCart();
        }

        driver.get("http://allegro.pl");
    }

/*
    @Test
    public void addToCartTest() {
        Header header = new Header(driver);
        SearchResultPage searchResultPage;
        ProductPage productPage;
        Cart cart;

        searchResultPage = header.searchProduct("zegarek");
        Assert.assertNotNull(searchResultPage);

        productPage = searchResultPage.chooseLink(2);
        Assert.assertNotNull(productPage);

        productPage.addToCart();
        cart = productPage.goToCart();
        Assert.assertNotNull(cart);
    }
*/

    @Test
    public void removeFromCartTest() throws InterruptedException {
        SearchResultPage searchResultPage;
        ProductPage productPage;
        Cart cart;

        searchResultPage = this.searchProductByCategory("Dziecko", "Odzież");
        Assert.assertNotNull(searchResultPage);

        productPage = searchResultPage.chooseLink(2);
        Assert.assertNotNull(productPage);

        productPage.addToCart();
        cart = productPage.goToCart();
        Assert.assertNotNull(cart);

      /*  searchResultPage = this.searchProductByCategory("Moda", "Odzież damska");
        Assert.assertNotNull(searchResultPage);

        productPage = searchResultPage.chooseLink(1);
        Assert.assertNotNull(productPage);
        productPage.addToCart();
        cart = productPage.goToCart();
        Assert.assertNotNull(cart);
        */

        cart.unselectAllCheckBox();
        this.selectCheckBox(0);
        cart = this.removeProduct();

    }

    @After
    public void closeWindow() {
//        driver.close();
    }


}
