package Test_Functionality;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.Runtime;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Excel_Row_Count {
    private WebDriver driver;
    public Excel_Row_Count(WebDriver driver)
    {
        this.driver=driver;
    }

    private static String dir = System.getProperty("user.dir");
    private static final String excel = dir + "/src/main/java/TestData/TestData.xlsx";

    @FindBy(xpath = "//button[contains(.,'Add User')]")
    private
    WebElement AddUserButton;



    @FindBy(xpath = "(//i[@ng-class='iconClass'])[3]")
    private
    WebElement DeleteUserIcon;

    @FindBy(xpath = "//button[contains(.,'OK')]")
    private
    WebElement OkDeleteUserButton;


    @FindBy(xpath = "//input[contains(@name,'FirstName')]")
    private
    WebElement FirstName;

    @FindBy(xpath = "//input[contains(@name,'LastName')]")
    private
    WebElement LastName;

    @FindBy(xpath = "//input[contains(@name,'UserName')]")
    private
    WebElement UserName;

    @FindBy(xpath = "//input[contains(@name,'Password')]")
    private
    WebElement Password;

    @FindBy(xpath = "//label[contains(.,'Company BBB')]")
    private
    WebElement Customer;

    @FindBy(xpath = "//select[contains(@name,'RoleId')]")
    private
    WebElement Role;

    @FindBy(xpath = "//input[contains(@type,'email')]")
    private
    WebElement Email;

    @FindBy(xpath = "//input[contains(@name,'Mobilephone')]")
    private
    WebElement MobilePhone;

    @FindBy(xpath = "//button[@ng-click='save(user)'][contains(.,'Save')]")
    private
    WebElement Button_Save;

    @FindBy(xpath = "(//td[contains(@class,'smart-table-data-cell')])[1]")
    private
    WebElement First_Row_Data;

    @FindBy(xpath = "(//td[@ng-hide='column.noList'])[2]")
    private
    WebElement Second_Record_Data;


    public void Click_Add_New_User_Button() {
        WebDriverWait wait_for_add_user_Button = new WebDriverWait(driver, 20);
        wait_for_add_user_Button.until(ExpectedConditions.visibilityOf(AddUserButton));
        AddUserButton.click();
    }

    public void Click_Delete_User_Button() {
        WebDriverWait wait_for_add_user_Button = new WebDriverWait(driver, 20);
        wait_for_add_user_Button.until(ExpectedConditions.visibilityOf(DeleteUserIcon));
        DeleteUserIcon.click();
    }

    public void Click_Ok_Delete_User_Button() {
        WebDriverWait wait_for_add_user_Button = new WebDriverWait(driver, 20);
        wait_for_add_user_Button.until(ExpectedConditions.visibilityOf(OkDeleteUserButton));
        OkDeleteUserButton.click();
    }
    public void Add_First_Name(String fn) {
        WebDriverWait wait_for_firstname = new WebDriverWait(driver, 10);
        wait_for_firstname.until(ExpectedConditions.visibilityOf(FirstName));
        FirstName.clear();
        FirstName.sendKeys(fn);
    }
    public void Add_Last_Name(String ln) {
        WebDriverWait wait_for_lastname = new WebDriverWait(driver, 10);
        wait_for_lastname.until(ExpectedConditions.visibilityOf(LastName));
        LastName.clear();
        LastName.sendKeys(ln);
    }
    public void Add_UserName(String un) {
        WebDriverWait wait_for_username = new WebDriverWait(driver, 10);
        wait_for_username.until(ExpectedConditions.visibilityOf(UserName));
        Runtime.Timestamp timestamp = new Runtime.Timestamp(System.currentTimeMillis());
        un+="_"+timestamp;
        UserName.clear();
        UserName.sendKeys(un);
    }
    public void Add_Password(String pn) {
        WebDriverWait wait_for_password = new WebDriverWait(driver, 10);
        wait_for_password.until(ExpectedConditions.visibilityOf(Password));
        Password.clear();
        Password.sendKeys(pn);
    }
    public void Add_Customer() {
        WebDriverWait wait_for_customer = new WebDriverWait(driver, 10);
        wait_for_customer.until(ExpectedConditions.visibilityOf(Customer));
        Customer.click();
    }
    public void Select_Role(String r_Role) {
        WebDriverWait wait_for_role = new WebDriverWait(driver, 10);
        wait_for_role.until(ExpectedConditions.visibilityOf(Role));
        Role.sendKeys(r_Role);
    }
    public void Add_Email(String em) {
        WebDriverWait wait_for_email = new WebDriverWait(driver, 10);
        wait_for_email.until(ExpectedConditions.visibilityOf(Email));
        Email.clear();
        Email.sendKeys(em);
    }
    public void Add_Mobile_Phone(String pn) {
        WebDriverWait wait_for_cellphone = new WebDriverWait(driver, 10);
        wait_for_cellphone.until(ExpectedConditions.visibilityOf(MobilePhone));
        MobilePhone.clear();
        MobilePhone.sendKeys(pn);
    }
    public void Click_Button_Save()
    {
        WebDriverWait wait_for_save_Button= new WebDriverWait(driver,10);
        wait_for_save_Button.until(ExpectedConditions.visibilityOf(Button_Save));
        Button_Save.click();
    }
    public void Verify_Added_User(String saved_fn,String saved_ln) throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);

        Assert.assertEquals(First_Row_Data.getText(),saved_fn);
        Assert.assertEquals(Second_Record_Data.getText(),saved_ln);
    }

    public void Verify_Deleted_User(String saved_fn,String saved_ln) throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);

        Assert.assertNotEquals(First_Row_Data.getText(),saved_fn);
        Assert.assertNotEquals(Second_Record_Data.getText(),saved_ln);
    }

    public void Excel_Row_Length() throws IOException {
        FileInputStream fis = new FileInputStream(excel);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int lastrow = sheet.getLastRowNum();

        for (int i = 1; i <= lastrow; i++){

            String first_name = sheet.getRow(i).getCell(0).getStringCellValue();
            String last_name = sheet.getRow(i).getCell(1).getStringCellValue();
            String user_name = sheet.getRow(i).getCell(2).getStringCellValue();
            String pass_word = sheet.getRow(i).getCell(3).getStringCellValue();
            String email = sheet.getRow(i).getCell(4).getStringCellValue();
            String cellphone = sheet.getRow(i).getCell(5).getStringCellValue();
            String role =sheet.getRow(i).getCell(6).getStringCellValue();

            Click_Add_New_User_Button();
            Add_First_Name(first_name);
            Add_Last_Name(last_name);
            Add_UserName(user_name);
            Add_Password(pass_word);
            Add_Email(email);
            Add_Mobile_Phone(cellphone);
            Add_Customer();

            Select_Role(role);
            Click_Button_Save();

        }


    }
}
