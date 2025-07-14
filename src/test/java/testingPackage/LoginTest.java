package testingPackage;

import base.BaseClass;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.WaitForSelectorState;
import org.example.tests.page.HomePage;
import org.example.tests.page.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.UtilClass;

import java.io.IOException;

public class LoginTest extends BaseClass {
    public static LoginPage loginPage;
    public static HomePage homePage;

    @BeforeClass
    public void settingUp() throws IOException {
        setUp();
        homePage=new HomePage(page);
        loginPage=new LoginPage(page);

    }
    @Test(dataProvider = "loginTest",dataProviderClass= UtilClass.class)
    public void loginTest(String username,String password){
        homePage.navigateToThePage("https://www.echannelling.com/");
        page.locator("xpath=/html/body/div[1]/div[1]/div/div/div[2]/button").click();
//        loginPage.setUsername(username);
//        loginPage.setPassword(password);
        page.getByPlaceholder("Enter your Member ID / Email / NIC").fill(username);
        page.getByPlaceholder("Enter your password").fill(password);
      //  loginPage.clickOnLoginButton();
        page.locator("xpath=/html/body/div[2]/div/div/div/div[2]/div[2]/form/div[4]/button/div/div[1]").click();
        page.getByText("Invalid Username or Password").waitFor(new Locator.WaitForOptions().setState(WaitForSelectorState.VISIBLE));
        Assert.assertTrue(page.getByText("Invalid Username or Password").isVisible());
    }
    @AfterClass
    public void tearDown(){
        browserContext.close();
        browser.close();
        playwright.close();
    }

}
