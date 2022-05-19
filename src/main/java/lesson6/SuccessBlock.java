package lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SuccessBlock extends BaseView {
    public SuccessBlock(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div [@class=\"layer_cart_product col-xs-12 col-md-6\"]")
    private WebElement successBlock;

    public SuccessBlock webDriverWaitSuccessBlock() {

        webDriverWait.until(ExpectedConditions.visibilityOf(successBlock));
        return new SuccessBlock(driver);
    }

    private final static String SUCCESS_XPATH_LOCATOR = "//div[contains(@class,'layer_cart_product')]//h2";

    @FindBy(xpath = SUCCESS_XPATH_LOCATOR)
    public WebElement successHeader;

    @FindBy(xpath = "//a[@title=\"Proceed to checkout\"]")
    private WebElement ProceedToCheckoutButton;

    public ShoppingCartPage clickProceedToCheckoutButton() {

        actions.moveToElement(ProceedToCheckoutButton).click()
                .build()
                .perform();
        return new ShoppingCartPage(driver);

    }
}

