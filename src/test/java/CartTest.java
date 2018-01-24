import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Created by Antonina on 2018-01-18.
 */
public class CartTest {
    public WebDriver driver;
    private static By addToCartPopUpCheck = By.cssSelector(".modal-content .modal-header");
    private static By searchResultPageCheck = By.cssSelector(".main-content .layout__right [data-box-name=items-v3]");
    private static By productPageCheck = By.cssSelector("[itemprop=offers]");

    private void clearCart() throws InterruptedException {
        Header header = new Header(driver);
        Cart cart = header.goToCartViaIcon();
        cart.clearCart();
        Assert.assertEquals((Integer) 0, header.checkIndexOfProductsInCart());
    }

    private SearchResultPage searchProductByCategory(String categoryName, String subCategoryName) throws InterruptedException {
        Header header = new Header(driver);
        WebElement categoryNameEl = header.searchCategory(categoryName);
        Actions actions = new Actions(driver);
        actions.moveToElement(categoryNameEl).build().perform();
        WebElement subCategoryNameEl = header.searchSubCategory(subCategoryName);
        actions.moveToElement(subCategoryNameEl).click().build().perform();
        return new SearchResultPage(driver);
    }

    private void selectCheckBox(int indexOfCheckBox) {
        Cart cart = new Cart(driver);
        cart.selectCheckBox(indexOfCheckBox).click();
    }

    @BeforeClass
    public static void setup() {
        String exePath = "D:\\drivers\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
    }

    @Before
    public void beforeTest() throws InterruptedException {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("http://allegro.pl");
        Header header = new Header(driver);
        if (!header.checkIndexOfProductsInCart().equals(0)) {
            this.clearCart();
        }
    }

    @Test
    public void addToCartTest() throws InterruptedException {
        Header header = new Header(driver);
        SearchResultPage searchResultPage;
        ProductPage productPage;

        searchResultPage = header.searchProduct("zegarek");
        Assert.assertTrue("Search result page didn't occur",driver.findElement(searchResultPageCheck)!=null);

        productPage = searchResultPage.chooseLink(2);
        Assert.assertTrue("Going to product page didn't succeed",driver.findElement(productPageCheck)!=null);

        int numberOfProductsInCart = header.checkIndexOfProductsInCart();
        productPage.addToCart();
        Assert.assertTrue("Adding product to cart didn't succeed",driver.findElement(addToCartPopUpCheck)!=null);
        productPage.goToCart();
        Assert.assertTrue("Going to cart didn't succeed", driver.getCurrentUrl().equals("https://allegro.pl/cart"));
        Assert.assertTrue("Adding product to cart didn't succeed", header.checkIndexOfProductsInCart() == (numberOfProductsInCart + 1));
    }


    @Test
    public void removeFromCartTest() throws InterruptedException {
        SearchResultPage searchResultPage;
        ProductPage productPage;
        Cart cart;
        Header header = new Header(driver);

        searchResultPage = this.searchProductByCategory("Dziecko", "Odzież");
        Assert.assertTrue("Search result page didn't occur",driver.findElement(searchResultPageCheck)!=null);

        productPage = searchResultPage.chooseLink(2);
        Assert.assertTrue("Going to product page didn't succeed",driver.findElement(productPageCheck)!=null);

        productPage.addToCart();
        Assert.assertTrue("Adding product to cart didn't succeed",driver.findElement(addToCartPopUpCheck)!=null);
        productPage.continueShopping();
        Assert.assertTrue("Going to product page didn't succeed",driver.findElement(productPageCheck)!=null);

        searchResultPage = this.searchProductByCategory("Motoryzacja", "Chemia");
        Assert.assertTrue("Search result page didn't occur",driver.findElement(searchResultPageCheck)!=null);

        productPage = searchResultPage.chooseLink(1);
        Assert.assertTrue("Going to product page didn't succeed",driver.findElement(productPageCheck)!=null);
        productPage.addToCart();
        Assert.assertTrue("Adding product to cart didn't succeed",driver.findElement(addToCartPopUpCheck)!=null);
        cart = productPage.goToCart();
        Assert.assertTrue("Going to cart didn't succeed", driver.getCurrentUrl().equals("https://allegro.pl/cart"));

        int numberOfProductsInCart = header.checkIndexOfProductsInCart();
        cart.unselectAllCheckBox();
        this.selectCheckBox(0);
        cart.removeProductFromCart();
        Assert.assertTrue("Removing product didn't succeed", header.checkIndexOfProductsInCart() == (numberOfProductsInCart - 1));

    }

    @After
    public void closeWindow() {
        driver.close();
    }


}
