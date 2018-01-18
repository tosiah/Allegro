import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Antonina on 2018-01-18.
 */
public class AddingToCartTest {
    public static WebDriver driver;

    private void clearCart() {
        Header header = new Header(driver);
        Cart cart = header.goToCartViaIcon();
        cart.clearCart();
        Assert.assertEquals((Integer) 0, header.checkIndexOfProductsInCart());
    }

    @BeforeClass
    public static void setup() {
        String exePath = "D:\\drivers\\chromedriver_win32\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", exePath);
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before
    public void beforeTest() {
        driver.get("http://allegro.pl");

        Header header = new Header(driver);
        if (!header.checkIndexOfProductsInCart().equals(0)) {
            this.clearCart();
        }
    }


    @Test
    public void test() {
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

    @Test
    public void test2() {
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

    @After
    public void closeWindow() {
//        driver.close();
    }


}
