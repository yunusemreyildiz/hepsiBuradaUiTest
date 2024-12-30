package pages;

import base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;

import java.time.Duration;

public class BasketPage extends TestBase {

    public static void compareTexts() {
        // İlk elementten metni al
        String textFromH1 = getProductNameFromProductDetail();

        // İkinci elementten metni al
        String textFromA = getProductNameFromBasket();

        // Metinleri karşılaştır
        if (textFromH1.equals(textFromA)) {
            System.out.println("Metinler eşit: " + textFromH1);
        } else {
            System.out.println("Metinler farklı! H1 Metni: " + textFromH1 + ", A Metni: " + textFromA);
        }
    }


    public static boolean checkBasketItemCount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Sepet öğesini bul
        WebElement basketItemCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='basket-item-count']")));

        // Sepet öğesinin içeriğini al
        String itemCountText = basketItemCountElement.getText().trim();

        // Fiyat 1 mi kontrol et
        if (itemCountText.equals("1")) {
            System.out.println("Sepet içinde 1 ürün var.");
            return true; // 1 ürün var
        } else {
            System.out.println("Sepet içinde 1 ürün yok, mevcut ürün sayısı: " + itemCountText);
            return false; // 1 ürün yok
        }
    }

    public static String getPriceFromProductDetail() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Fiyat elementini bul
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-test-id='price-current-price']")));

        // Tam metni al ve boşlukları kaldır
        String priceText = priceElement.getText().trim();

        // Fiyat metnini döndür
        return priceText;
    }
    public static String getProductPriceOnBasket(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));

        // Fiyat elementini bul
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='product_price_uXU6Q']")));

        // Fiyat metnini döndür
        return priceElement.getText().trim();

    }

    public static void comparePrices() {
        // İlk elementten fiyatı al
        String price1 = getPriceFromProductDetail();

        // İkinci elementten fiyatı al
        String price2 = getProductPriceOnBasket();

        // Fiyatları karşılaştır
        if (price1.equals(price2)) {
            System.out.println("Fiyatlar eşit: " + price1);
        } else {
            System.out.println("Fiyatlar farklı! İlk fiyat: " + price1 + ", İkinci fiyat: " + price2);
        }
    }

    public static String getProductNameFromProductDetail() {
        // WebElement'i bulun
        WebElement h1Element = driver.findElement(By.xpath("//h1[@data-test-id='title']"));

        // Metni alın
        return h1Element.getText().trim();
    }

    public static String getProductNameFromBasket() {
        // WebElement'i bulun
        WebElement productNameElement = driver.findElement(By.xpath("//div[@class='product_name_2Klj3']/a"));

        // Metni alın ve boşlukları temizleyin
        return productNameElement.getText().trim();
    }

}
