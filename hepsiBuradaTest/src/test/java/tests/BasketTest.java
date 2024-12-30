package tests;

import base.TestBase;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.BasketPage;
import pages.CategoryPage;
import pages.HomePage;
import pages.ProductPage;
import utils.Constants;


public class BasketTest extends TestBase {

    private static Logger log  = Logger.getLogger(BasketTest.class);

    @Before
    public void setup(){
        setDriver();
        PropertyConfigurator.configure(Constants.LOG4J_PROPERTIES_FILE_PATH);
    }

    @After
    public void teardown(){
        closeDriver();
        log.info("Chromedriver has been finished, test is over.");
    }

    @Given("Navigate to website")
    public void navigate_to_website() throws InterruptedException {
        navigateToHome();
        closePopup();
    }

    @When("Check home page is loaded")
    public void check_home_page_is_loaded() {
       isPageLoaded();
    }
    @When("Get cursor to the selected category")
    public void get_cursor_to_the_category() throws InterruptedException {
        HomePage.cursorToHoverMenu();
        HomePage.cursorToSubHoverMenu();
        HomePage.cursorToSecondSubHoverMenu();

    }
    @And("Choose Apple option from filter menu")
    public void click_option_from_hamburger_menu() throws InterruptedException {
        CategoryPage.clickOnBrand();
        reloadPage();
    }
    @When("Click on tablet size")
    public void click_on_tablet_size() throws InterruptedException {
        CategoryPage.clickOnTabletSize();
    }

    @When("Click on highest price element")
    public void click_highest_item() throws InterruptedException {
        CategoryPage.clickOnHighestPriceProduct();
    }
    @When("Get product's price and click add to cart button")
    public void add_to_cart() throws InterruptedException {
        isPageLoaded();
        CategoryPage.switchToSecondTab();
        BasketPage.getPriceFromProductDetail();
        ProductPage.clickAddToCartButton();
    }
    @When("Click on go to the basket button and get price")
    public void click_to_button_for_add_to_basket() {
        CategoryPage.clickFlyBasketButton();
        BasketPage.getProductPriceOnBasket();
    }

    @Then("Compare prices and check the basket")
    public void control_item() throws InterruptedException {
        BasketPage.comparePrices();
        BasketPage.checkBasketItemCount();
    }

}
