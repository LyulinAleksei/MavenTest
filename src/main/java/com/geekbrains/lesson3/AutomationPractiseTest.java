package com.geekbrains.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AutomationPractiseTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement womenButton = driver.findElement(By.xpath("//a[@title=\"Women\"]"));
        womenButton.click();
        driver.findElement(By.id("layered_category_4")).click();
        driver.findElement(By.id("layered_id_attribute_group_3")).click();
        driver.findElement(By.id("layered_id_attribute_group_11")).click();
        driver.findElement(By.xpath("//a[@title=\"Add to cart\"]")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@title=\"Proceed to checkout\"]")));
        driver.findElement(By.xpath("//a[@title=\"Proceed to checkout\"]")).click();
        driver.findElement(By.xpath("//i[@class=\"icon-trash\"]")).click();

        Thread.sleep(7000);
        driver.quit();
    }
}
