package base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Constants;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver; // Global driver
    public static Logger log = Logger.getLogger(TestBase.class);

    public static void setDriver() {
        System.setProperty("webdriver.chrome.driver", "/Users/yunusemreyildiz/Downloads/hepsiBuradaTest/src/test/java/drivers/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options); // Global `driver` kullanılıyor
        log.info("ChromeDriver initialized.");
    }


    public static void closePopup() throws InterruptedException {
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]"))).click();
        log.info("Closed pop up.");
    }

    public static void reloadPage() {
        try {
            // Tarayıcıyı yeniden yükle
            driver.navigate().refresh();
            System.out.println("Sayfa başarıyla yenilendi.");
        } catch (Exception e) {
            System.out.println("Sayfa yenilenirken bir hata oluştu: " + e.getMessage());
        }
    }

    public static void navigateToHome() {
        driver.get(Constants.BASE_URL); // Global `driver` kullanılıyor
        log.info("Navigated to home page: " + Constants.BASE_URL);
        driver.manage().window().maximize();
        log.info("Chrome window maximized.");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public static void isPageLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver; // Global `driver` kullanılıyor
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(webDriver ->
                js.executeScript("return document.readyState;").equals("complete"));
        log.info("Page loaded successfully.");
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            log.info("Driver closed.");
        } else {
            log.warn("Driver was not initialized. Nothing to close.");
        }
    }
}
