package testingPackage;

import base.BaseClass;
import com.microsoft.playwright.*;
import org.example.tests.page.HomePage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;


public class FirstClass extends BaseClass {

private HomePage homePage;
    @BeforeClass
    public void settingUp() throws IOException {
        setUp();
        homePage=new HomePage(page);
    }
    @Test
    public void testOne(){
        homePage.navigateToThePage("https://www.example.com/");
       homePage.hoverTheElement();
       homePage.selectTheDev("Support");
        Assert.assertEquals(page.title(),"Support | BrowserStack");
       homePage.searchInput("playwright");
       page.keyboard().press("Enter");
       page.waitForURL("https://www.example.com/search?query=playwright&type=support&referrer=support");
Assert.assertTrue(page.url().contains("https://www.example.com/search?query=playwright&type=support&referrer=support"));
        }


    @AfterClass
    public void tearDown() throws InterruptedException {

        browserContext.close();
        browser.close(new Browser.CloseOptions().setReason("close as the test completion"));
        playwright.close();


    }
}
