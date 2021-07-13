package com.demoblaze.pages;

import com.demoblaze.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FormPage {
    public FormPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//input[@id='name']")
    public WebElement nameInputBox;

    @FindBy(xpath = "//input[@id='country']")
    public WebElement countryInputBox;

    @FindBy(xpath = "//input[@id='city']")
    public WebElement cityInputBox;

    @FindBy(xpath = "//input[@id='card']")
    public WebElement creditCardInputBox;

    @FindBy(xpath = "//input[@id='month']")
    public WebElement monthInputBox;

    @FindBy(xpath = "//input[@id='year']")
    public WebElement yearInputBox;

    @FindBy(xpath = "//button[.='Purchase']")
    public WebElement purchaseButton;

    @FindBy(xpath = "//p[@style='display: block;']")
    public WebElement receipt;


}
