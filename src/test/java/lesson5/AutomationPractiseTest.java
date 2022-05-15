package lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AutomationPractiseTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        driver.get("http://automationpractice.com/index.php");
    }

    @Test
    void openPageWomen() {
        actions.moveToElement(driver.findElement(By.xpath("//a[@title=\"Women\"]"))).click().
                build().
                perform();
        Assertions.assertTrue(driver.getCurrentUrl().contains("controller=category"));
    }

    @Test
    void enabledFilters() {
        driver.findElement(By.xpath("//a[@title=\"Women\"]")).click();
        driver.findElement(By.id("layered_category_4")).click();
        driver.findElement(By.id("layered_id_attribute_group_3")).click();
        driver.findElement(By.id("layered_id_attribute_group_11")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id=\"enabled_filters\"]/span")));
        Assertions.assertTrue(driver.getPageSource().contains("Enabled filters"));
    }

    @Test
    void addToCart()  {
        driver.findElement(By.xpath("//a[@title=\"Women\"]")).click();
        driver.findElement(By.id("layered_category_4")).click();
        driver.findElement(By.id("layered_id_attribute_group_3")).click();
        driver.findElement(By.id("layered_id_attribute_group_11")).click();
        driver.findElement(By.xpath("//a[@title=\"Add to cart\"]")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div [@class=\"layer_cart_product col-xs-12 col-md-6\"]")));
        Assertions.assertTrue(driver.findElement(By.xpath("//div [@class=\"layer_cart_product col-xs-12 col-md-6\"]")).isDisplayed());
    }

    @Test
    void deleteFromCart() {
        driver.findElement(By.xpath("//a[@title=\"Women\"]")).click();
        driver.findElement(By.id("layered_category_4")).click();
        driver.findElement(By.id("layered_id_attribute_group_3")).click();
        driver.findElement(By.id("layered_id_attribute_group_11")).click();
        driver.findElement(By.xpath("//a[@title=\"Add to cart\"]")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title=\"Proceed to checkout\"]")));
        driver.findElement(By.xpath("//a[@title=\"Proceed to checkout\"]")).click();
        driver.findElement(By.xpath("//i[@class=\"icon-trash\"]")).click();
        Assertions.assertTrue(driver.getPageSource().contains("Your shopping cart is empty"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}


