package com.demoblaze.pages;

import com.demoblaze.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LaptopsPage {
    public LaptopsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@class = 'hrefch']")
    public List<WebElement> laptopsList;

    @FindBy(xpath = "//h3[@class='price-container']")
    public WebElement priceContainer;
}
