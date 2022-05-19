package lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class WomenPage extends BaseView {

    public WomenPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//ul[@id=\"ul_layered_category_0\"]//a")
    private List<WebElement> categoriesList;

    public WomenPage selectCategories(String category) {
        categoriesList.stream().filter(s -> s.getText().contains(category)).findFirst().get().click();
        return this;
    }

    @FindBy(xpath = "//ul[@id='ul_layered_id_attribute_group_1']//a")
    private List<WebElement> sizesList;

    public WomenPage selectSize(String size) {
        sizesList.stream().filter(s -> s.getText().contains(size)).findFirst().get().click();
        return this;
    }

    @FindBy(xpath = "//ul[@id='ul_layered_id_attribute_group_3']//a")
    private List<WebElement> colorList;

    public WomenPage selectColor(String color) {
        colorList.stream().filter(s -> s.getText().contains(color)).findFirst().get().click();
        return this;
    }

    @FindBy(id = "enabled_filters")
    private WebElement enabled_filters;

    public void webDriverWaitEnabledFilters() {
        webDriverWait.until(ExpectedConditions.visibilityOf(enabled_filters));
    }

    @FindBy(xpath = "//ul[@class='product_list grid row']/li")
    private List<WebElement> productList;

    @FindBy(xpath = "//a[@title='Add to cart']")
    private WebElement addToCartButton;

    public SuccessBlock addToCartByText(String productName) {
        actions.moveToElement(productList.stream().filter(t -> t.getText().contains(productName)).findFirst().get())
                .build().perform();
        addToCartButton.click();
        return new SuccessBlock(driver);
    }
}

