package lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BaseView {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//i[@class=\"icon-trash\"]")
    private WebElement deleteButton;
    @Step("Удаляем товар из корзины")
    public ShoppingCartPage DeleteProduct() {
        actions.moveToElement(deleteButton).click()
                .build().perform();
        return new ShoppingCartPage(driver);
    }
}
