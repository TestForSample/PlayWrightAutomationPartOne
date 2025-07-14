package testingPackage;

import com.microsoft.playwright.*;
import org.example.tests.page.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstClass {
private Playwright playwright;
private Browser browser;
private Page page;
private HomePage homePage;
    @BeforeClass
    public void setUp(){
        playwright=Playwright.create();
        browser=playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page=browser.newPage();
        page.setViewportSize(1500,703);
        homePage=new HomePage(page);
    }
    @Test
    public void testOne(){
        homePage.navigateToThePage("https://www.browserstack.com/");
       homePage.hoverTheElement();
       homePage.selectTheDev("Support");
        }


    @AfterClass
    public void tearDown(){
        browser.close(new Browser.CloseOptions().setReason("close as the test completion"));
    }
}
