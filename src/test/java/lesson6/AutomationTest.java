package lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationTest {
    WebDriver driver;
    WomenPage womenPage;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        womenPage = new WomenPage(driver);
    }

    @Test
    void enabledFilters() {
        driver.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        new WomenPage(driver)
                .selectCategories("Tops")
                .selectSize("L")
                .selectColor("Black")
                .webDriverWaitEnabledFilters();
        Assertions.assertTrue(driver.getPageSource().contains("Enabled filters:"));
    }

    @Test
    void addToCart() {
        driver.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        new WomenPage(driver)
                .addToCartByText("Printed Dress")//вопрос-почему всегда добавляет только Faded Short если выбран Printed Dress???
                .webDriverWaitSuccessBlock();
        Assertions.assertTrue(new SuccessBlock(driver).successHeader.isDisplayed());
    }

    @Test
    void deleteFromCart() {
        driver.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        new WomenPage(driver)
                .addToCartByText("Printed Dress")//вопрос-почему всегда добавляет только Faded Short если выбран Printed Dress???
                .webDriverWaitSuccessBlock()
                .clickProceedToCheckoutButton()
                .DeleteProduct();
        Assertions.assertTrue(driver.getPageSource().contains("Your shopping cart is empty"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}

