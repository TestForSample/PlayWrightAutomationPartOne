package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseClass {

    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
    public static Page page;
    public static Playwright playwright;
    public static Browser browser;
    public static BrowserContext browserContext;

    public void setUp() throws IOException {
        playwright= Playwright.create();
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Path videoDir= Paths.get("videos");
        Files.createDirectories(videoDir);
        Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                .setViewportSize(1500, 720)
                .setRecordVideoDir(videoDir)
                .setRecordVideoSize(1280, 720);
        browserContext=browser.newContext(contextOptions);
        page=browserContext.newPage();
    }

@BeforeSuite
    public void setUpReport(){
        ExtentSparkReporter extentSparkReporter=new ExtentSparkReporter("testReport/TestReport.html");
        extentReports=new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
    }
    @AfterSuite
    public void tearDownReport() {
        extentReports.flush();
    }

    @BeforeMethod
    public void registerTest(Method method) {
        extentTest = extentReports.createTest(method.getName());
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Test passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            extentTest.skip(result.getThrowable());
        }

    }


}
