package com.demoblaze.step_definitions;

import com.demoblaze.pages.CartPage;
import com.demoblaze.pages.DemoblazePage;
import com.demoblaze.pages.FormPage;
import com.demoblaze.pages.LaptopsPage;
import com.demoblaze.utilities.BrowserUtils;
import com.demoblaze.utilities.ConfigurationReader;
import com.demoblaze.utilities.Driver;
import com.github.javafaker.Faker;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlineOrder_stepDef {
    private static int priceInCart;
    private static long id;





    @Given("User on the home page")
    public void user_on_the_home_page() {
        Driver.getDriver().get(ConfigurationReader.getProperty("demoblazeURL"));

        BrowserUtils.sleep(1);
    }

    @When("User see the Categories options")
    public void user_see_the_categories_options() {
        DemoblazePage dmbl = new DemoblazePage();
        for (WebElement each : dmbl.categoriesOptions) {
            System.out.println("Categories// " + each.getText());
        }
        Assert.assertFalse(dmbl.categoriesOptions.isEmpty());
        BrowserUtils.sleep(1);
    }

    @Then("User should be able to click on each Option")
    public void user_should_be_able_to_click_on_each_option() {
        DemoblazePage dmbl = new DemoblazePage();
        for (WebElement each : dmbl.categoriesOptions) {
            Assert.assertFalse(each.getAttribute("href").isEmpty());
        }
        BrowserUtils.sleep(1);
    }

    @When("User navigates to Laptop page")
    public void userNavigatesToLaptopPage() {
        DemoblazePage dmbl = new DemoblazePage();
        dmbl.laptopsLink.click();
        BrowserUtils.sleep(1);
    }

    @And("add {string} to the cart")
    public void addToTheCart(String item) {
        LaptopsPage lp = new LaptopsPage();
        WebElement product = findItemInProductList(item, lp.laptopsList);

        if (product == null) {
            throw new NoSuchElementException("Item not found");
        }
        product.click();

        BrowserUtils.sleep(1);
        addToCart();
        BrowserUtils.sleep(1);
    }


    private WebElement findItemInProductList(String item, List<WebElement> list) {
        for (WebElement each : list) {
            if (each.getText().equals(item)) {
                return each;
            }
        }
        System.out.println("Item not found");
        return null;
    }

    @When("User accepts alert")
    public void user_accepts_alert() throws InterruptedException {
        BrowserUtils.sleep(1);
        Alert alert = Driver.getDriver().switchTo().alert();
        alert.accept();
        user_on_the_home_page();
        BrowserUtils.sleep(1);
    }

    public static void addToCart() {
        BrowserUtils.sleep(1);
        WebElement addToCart = Driver.getDriver().findElement(By.xpath("//a[.='Add to cart']"));
        addToCart.click();
    }

    @When("User navigates to Cart")
    public void user_navigates_to_cart() {
        DemoblazePage dblz = new DemoblazePage();
        dblz.cart.click();
        BrowserUtils.sleep(1);
    }

    @Then("User should see {string} in the cart")
    public void userShouldSeeInTheCart(String item) {
        CartPage cp = new CartPage();
        for (WebElement each : cp.productListInCart) {
            if (each.getText().equals(item)) {
                Assert.assertEquals(each.getText(), item);
                priceInCart+=Integer.parseInt(Driver.getDriver().findElement(By.xpath("//tbody//tr/td[3]")).getText());
               // System.out.println(priceInCart);
                return;
            }
        }
        Assert.fail("Item not found" + item);
    }

    @And("delete {string} from the cart")
    public void deleteFromTheCart(String item) {
        CartPage cp = new CartPage();

        for (WebElement each : cp.productListInCart) {
            if (each.getText().equals(item)) {
               del(item);
               return;
            }
        }
        System.out.println("No item found: " + item);
        Assert.fail();
    }

    private void del (String item) {

        WebElement del = Driver.getDriver().findElement(By.xpath("//table//td[.='" + item + "']//../td[4]//a"));
        WebElement price = Driver.getDriver().findElement(By.xpath("//table//td[.='"+ item + "']//../td[3]"));
        priceInCart-=Integer.parseInt(price.getText());
       // System.out.println(priceInCart);
        del.click();
    }

    @Then("User should see that {string} is deleted from shopping cart")
    public void userShouldSeeThatIsDeletedFromShoppingCart(String item) {
        CartPage cp = new CartPage();
        BrowserUtils.sleep(2);
        for (WebElement each : cp.productListInCart) {
            if (each.getText().equals(item)) {
                Assert.fail();
            }
        }

    }


    @When("cart is not empty")
    public void cart_is_not_empty() {
        CartPage cp = new CartPage();
        Assert.assertTrue(cp.productListInCart.size()>0);
    }

    @When("User clicks on Place Order")
    public void user_clicks_on_place_order() {
        CartPage cp = new CartPage();
        cp.placeOrderButton.click();
    }

    @When("User fills in all place order form fields")
    public void user_fills_in_all_place_order_form_fields() {
        BrowserUtils.sleep(1);
        FormPage fp = new FormPage();
        Faker faker = new Faker();
        fp.nameInputBox.sendKeys(faker.name().fullName());
        fp.countryInputBox.sendKeys(faker.country().name());
        fp.cityInputBox.sendKeys(faker.country().capital());
        fp.creditCardInputBox.sendKeys(faker.business().creditCardNumber());
        fp.monthInputBox.sendKeys(faker.funnyName().name());
        fp.yearInputBox.sendKeys(faker.harryPotter().character());
    }

    @When("clicks on Purchase")
    public void clicks_on_purchase() {
        FormPage fp = new FormPage();
        fp.purchaseButton.click();
    }

    @Then("Purchase amount must be equal expected")
    public void purchase_amount_must_be_equal_expected() {
        FormPage fp = new FormPage();
        BrowserUtils.sleep(1);
        String log = fp.receipt.getText();
        System.out.println("log = " + log);
        List<String> list = new ArrayList(Arrays.asList(log.split("\n")));

        int amount = 0;
        for (String each : list) {
            if (each.startsWith("Id: ")) {
                id = Integer.parseInt(each.replace("Id: ",""));
            }
            if (each.startsWith("Amount: ")) {
                amount = Integer.parseInt(each.replace("Amount: ", "").replace(" USD",""));
            }
        }
       // System.out.println("Amount " + amount);
        Assert.assertEquals(amount, priceInCart);


    }



}



