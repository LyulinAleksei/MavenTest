package lesson7;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;

public class CustomLogger implements WebDriverListener {
    public static Logger logger = LoggerFactory.getLogger(CustomLogger.class);

    public void beforeFindElements(WebDriver driver, By locator) {
        logger.info("Ищем элемент по локатору" + locator.toString());
        Allure.step("Ищем элемент по локатору" + locator.toString());


    }

    public void beforeQuit(WebDriver driver) {
        Allure.addAttachment("Скриншот страницы перед поиском элемента",
                new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

}
