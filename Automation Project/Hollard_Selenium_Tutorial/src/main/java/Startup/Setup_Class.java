package Startup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Setup_Class {

    public WebDriver driver;

    @FindBy(xpath = "/html//table[@class='smart-table table table-striped']")
    private
    WebElement UserTable;

    private static String PAGE_URL = "http://www.way2automation.com/angularjs-protractor/webtables/";


    public Setup_Class(WebDriver driver) {
        this.driver = driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }
    public void Verify_Correct_User_Table()
    {
        WebDriverWait user_table = new WebDriverWait(driver,10);
        user_table.until(ExpectedConditions.visibilityOf(UserTable));
        Assert.assertTrue(UserTable.isDisplayed());
    }


}
