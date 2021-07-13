package com.demoblaze.pages;

import com.demoblaze.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    public CartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//tbody//tr/td[2]")
    public List<WebElement> productListInCart;

    @FindBy(xpath = "//button[.='Place Order']")
    public WebElement placeOrderButton;
}
