package pages;

import base.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;


import java.time.Duration;

public class ProductPage extends TestBase {

    public static void clickAddToCartButton() throws InterruptedException {
        // WebDriverWait tanımla
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        WebElement addToCartButton = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-test-id='addToCart' and text()='Sepete ekle']"))
        );

        // Tıklama işlemi
        addToCartButton.click();
    }

}
