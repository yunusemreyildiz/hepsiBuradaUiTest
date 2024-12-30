package pages;

import base.TestBase;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import java.time.Duration;
import java.util.*;

public class CategoryPage extends TestBase {

    public static void clickOnBrand() throws InterruptedException {
        // WebDriverWait tanımla
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Apple checkbox öğesini bul ve görünür olmasını bekle
        WebElement appleCheckbox = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(@class, 'checkbox-RbVhSW7HY41omgqYfVMo')]//input[@value='apple']"))
        );

        // Kaydırma işlemi için JavascriptExecutor kullan
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", appleCheckbox);

        Thread.sleep(3500);
        // Tıklama işlemi
        wait.until(ExpectedConditions.elementToBeClickable(appleCheckbox)).click();

    }

    public static void switchToSecondTab() {
        // Tüm açık pencere/sekme kimliklerini al
        Set<String> allWindows = driver.getWindowHandles();

        // İlk sekmeye (aktif pencere) geçtikten sonra ikinci sekmeye geçiş yap
        Iterator<String> iterator = allWindows.iterator();

        // İlk sekmeyi al ve geç
        String firstTab = iterator.next();

        // İkinci sekmeye geç
        String secondTab = iterator.next();
        driver.switchTo().window(secondTab);
    }

    public static void clickOnTabletSize() throws InterruptedException {
        // WebDriverWait tanımla
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // 13,2 inç tablet bağlantısını bul ve tıklanabilir olmasını bekle
        WebElement tabletLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='13,2 inç']"))
        );

        // Tıklama işlemi
        tabletLink.click();
    }

    public static void clickOnHighestPriceProduct() throws InterruptedException {
        // WebDriverWait tanımla
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Fiyatları içeren tüm div'leri bulmak için genel bir XPath kullanıyoruz
        List<WebElement> priceElements = driver.findElements(By.xpath("//div[@data-test-id='price-current-price']"));

        // Fiyatları ve ürün bağlantılarını saklamak için listeler oluştur
        List<Double> prices = new ArrayList<>();
        List<WebElement> productLinks = new ArrayList<>();

        // Fiyatları ve ürün linklerini listeye ekle
        for (WebElement priceElement : priceElements) {
            String priceText = priceElement.getText().replace(" TL", "").replace(".", "").replace(",", ".");
            try {
                double price = Double.parseDouble(priceText);
                prices.add(price);

                // Ürünün bağlantısını al
                WebElement productLink = priceElement.findElement(By.xpath("ancestor::a"));
                productLinks.add(productLink);
            } catch (NumberFormatException e) {
                // Hatalı fiyat formatını atla
                continue;
            }
        }

        // En yüksek fiyatı bul
        double highestPrice = Collections.max(prices);

        // En yüksek fiyatın indexini bul
        int highestPriceIndex = prices.indexOf(highestPrice);

        // En yüksek fiyatlı ürünü tıklamak için ilgili ürünü bul
        WebElement highestPriceProductLink = productLinks.get(highestPriceIndex);

        // Kaydırma işlemi için JavascriptExecutor kullan
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({ behavior: 'smooth', block: 'center' });", highestPriceProductLink);

        // Tıklama işlemi
        wait.until(ExpectedConditions.elementToBeClickable(highestPriceProductLink)).click();
    }

    public static void clickFlyBasketButton(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Sepete git']"))).click();
        log.info("Clicked on Flycart icon.");

    }

}
