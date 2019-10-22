package Test_Helpers;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Report_Helper {

    private ExtentHtmlReporter htmlReporter;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void reportSetup()
    {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Add_New_User_Test_Reports/AddAndVerifyUser.html");
        htmlReporter.config().setDocumentTitle("And And Verify User Tests");
        htmlReporter.config().setReportName("Add And Verify User");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extent= new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Hostname","Localhost");
        extent.setSystemInfo("OS","Windows");
        extent.setSystemInfo("Environment","Test_Env");
    }
    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed Is " + result.getName());
            test.log(Status.FAIL, "Test Case Failed Is " + result.getThrowable());

        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped Is " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case Passed Is " + result.getName());
        }

    }
    @AfterSuite
    public void reportTeardown()
    {
        extent.flush();

    }
}