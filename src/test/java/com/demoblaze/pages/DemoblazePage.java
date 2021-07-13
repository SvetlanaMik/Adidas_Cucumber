package com.demoblaze.pages;

import com.demoblaze.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DemoblazePage {
    public DemoblazePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[.='Phones']")
    public WebElement phonesLink;

    @FindBy(xpath = "//a[.='Laptops']")
    public WebElement laptopsLink;

    @FindBy(xpath = "//a[.='Monitors']")
    public WebElement monitorsLink;

    @FindBy(xpath = "//a[.='CATEGORIES']")
    public WebElement categoriesLink;

    @FindBy(xpath = "//a[@id='itemc']")
    public List<WebElement> categoriesOptions;

    @FindBy(xpath = "//a[.='Cart']")
    public WebElement cart;
}
