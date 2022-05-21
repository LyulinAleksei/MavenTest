package lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import lesson7.CustomLogger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;


@Epic("Интернет-магазин одежды")
public class AutomationTest {
    WebDriver driver;
    WomenPage womenPage;

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new EventFiringDecorator(new CustomLogger()).decorate(new ChromeDriver());
        womenPage = new WomenPage(driver);
    }

    @Test
    @Feature("Фильтры")
    @Story("Отображение выбранных фильтров")
    @Step("Проверяем что фильтры появились")
    @TmsLink("12345")
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
    @Feature("Корзина")
    @Story("Добавление товара в корзину")
    @Step("Проверяем что товар добавился в корзину")
    @TmsLink("123456")
    void addToCart() {
        driver.get("http://automationpractice.com/index.php?id_category=3&controller=category");
        new WomenPage(driver)
                .addToCartByText("Printed Dress")//вопрос-почему всегда добавляет только Faded Short если выбран Printed Dress???
                .webDriverWaitSuccessBlock();
        Assertions.assertTrue(new SuccessBlock(driver).successHeader.isDisplayed());
    }

    @Test
    @Feature("Корзина")
    @Story("Удаление товара из корзины")
    @Step("Проверяем что товар удалился из корзины")
    @TmsLink("1234567")
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
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry logEntry : logEntries) {
            Allure.addAttachment("Элемент лога браузера", logEntry.getMessage());

        }
        driver.quit();
    }
}

